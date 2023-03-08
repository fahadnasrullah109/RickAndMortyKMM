package com.rickandmortykmm.domain.models

import com.rickandmortykmm.CommonParcelable
import com.rickandmortykmm.CommonParcelize
import com.rickandmortykmm.data.local.dao.CharacterDao

@CommonParcelize
data class CharacterDomainModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    //val location: Location,
    val image: String,
    val episode: List<String>,
    val created: String
) : CommonParcelable

fun CharacterDomainModel.asDao(): CharacterDao {
    return CharacterDao().also {
        it.id = this.id
        it.name = this.name
        it.status = this.status
        it.species = this.species
        //it.location = this.location
        it.image = this.image
        it.episode = this.episode
        it.created = this.created
    }
}