package com.rickandmortykmm.data.local.service

import com.rickandmortykmm.data.local.dao.CharacterDao
import com.rickandmortykmm.data.local.dao.LastSyncDao

interface IRealmService {
    suspend fun saveCharacters(characters: List<CharacterDao>)
    suspend fun getCharacters(): List<CharacterDao>
    suspend fun getLastSyncTime(): LastSyncDao?
    suspend fun saveLastSyncTime()
}