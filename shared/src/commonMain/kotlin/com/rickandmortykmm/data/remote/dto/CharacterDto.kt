package com.rickandmortykmm.data.remote.dto

import com.rickandmortykmm.data.local.dao.CharacterDao
import com.rickandmortykmm.domain.models.CharacterDomainModel

@kotlinx.serialization.Serializable
data class CharacterDto(val info: Info, val results: List<Character>)

@kotlinx.serialization.Serializable
data class Info(val count: Int, val pages: Int, val next: String? = null)

@kotlinx.serialization.Serializable
data class Character(
    val id: Int, val name: String, val status: String, val species: String,
    //val location: Location,
    val image: String, val episode: List<String>, val created: String
)

@kotlinx.serialization.Serializable
data class Location(val name: String, val url: String)


fun CharacterDto.asDomainModel(): List<CharacterDomainModel> {
    return this.results.map {
        CharacterDomainModel(
            id = it.id, name = it.name, status = it.status, species = it.species,
            //location = it.location,
            image = it.image, episode = it.episode, created = it.created
        )
    }
}

fun CharacterDto.asDao(): List<CharacterDao> {
    return this.results.map {
        CharacterDao().apply {
            id = it.id
            name = it.name
            status = it.status
            species = it.species
            //location = it.location,
            image = it.image
            episode = it.episode
            created = it.created
        }
    }
}