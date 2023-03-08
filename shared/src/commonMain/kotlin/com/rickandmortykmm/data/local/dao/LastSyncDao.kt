package com.rickandmortykmm.data.local.dao

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class LastSyncDao : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var time: Long = 0L
}