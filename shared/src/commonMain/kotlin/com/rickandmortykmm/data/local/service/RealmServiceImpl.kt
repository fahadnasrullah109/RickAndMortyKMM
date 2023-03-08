package com.rickandmortykmm.data.local.service

import com.rickandmortykmm.data.local.dao.CharacterDao
import com.rickandmortykmm.data.local.dao.LastSyncDao
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import kotlinx.datetime.Clock

class RealmServiceImpl(private val realm: Realm) : IRealmService {

    override suspend fun saveCharacters(characters: List<CharacterDao>) {
        realm.writeBlocking {
            delete<CharacterDao>()
            characters.forEach {
                this.copyToRealm(
                    it
                )
            }
        }
    }

    override suspend fun getCharacters() = realm.query<CharacterDao>().find()
    override suspend fun getLastSyncTime() = realm.query<LastSyncDao>().first().find()

    override suspend fun saveLastSyncTime() {
        realm.writeBlocking {
            delete<LastSyncDao>()
            this.copyToRealm(instance = LastSyncDao().apply {
                time = Clock.System.now().toEpochMilliseconds()
            }, updatePolicy = UpdatePolicy.ALL)
        }
    }
}