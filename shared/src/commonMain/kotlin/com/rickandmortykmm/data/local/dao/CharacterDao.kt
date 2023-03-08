package com.rickandmortykmm.data.local.dao

import com.rickandmortykmm.data.remote.dto.Character
import com.rickandmortykmm.data.remote.dto.CharacterDto
import com.rickandmortykmm.data.remote.dto.Info
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class CharacterDao : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var status: String = ""
    var species: String = ""

    //var location: Location? = null
    var image: String = ""
    var episode: List<String> = listOf()
    var created: String = ""
}

data class Location(val name: String, val url: String)

fun CharacterDao.asDto() = Character(
    id, name, status, species, image, episode, created
)

fun List<CharacterDao>.asDto(): CharacterDto {
    val results = mutableListOf<Character>()
    this.forEach {
        results.add(it.asDto())
    }
    return CharacterDto(info = Info(count = this.size, pages = this.size), results = results)
}
