package com.rickandmortykmm.data.repository

import com.rickandmortykmm.data.local.dao.LastSyncDao
import com.rickandmortykmm.data.local.dao.asDto
import com.rickandmortykmm.data.local.service.IRealmService
import com.rickandmortykmm.data.remote.dto.CharacterDto
import com.rickandmortykmm.data.remote.dto.asDao
import com.rickandmortykmm.data.remote.service.IKtorService
import com.rickandmortykmm.domain.repo.ICharacterRepo
import kotlinx.datetime.Clock

class CharacterRepoImpl(
    private val ktorService: IKtorService, private val realmService: IRealmService
) : ICharacterRepo {
    override suspend fun getAllCharacters(): CharacterDto {
        val lastSyncDao = realmService.getLastSyncTime()
        return if (shouldLoadFromServer(lastSyncDao)) {
            val response = ktorService.getAllCharacters()
            realmService.saveCharacters(response.asDao())
            realmService.saveLastSyncTime()
            response
        } else {
            var characters = realmService.getCharacters()
            characters.asDto()
        }
    }

    private fun shouldLoadFromServer(lastSyncDao: LastSyncDao?): Boolean {
        return if (lastSyncDao == null) {
            true
        } else {
            val diff = Clock.System.now().toEpochMilliseconds() - lastSyncDao.time
            diff > 60 * 5 * 1000
        }
    }
}