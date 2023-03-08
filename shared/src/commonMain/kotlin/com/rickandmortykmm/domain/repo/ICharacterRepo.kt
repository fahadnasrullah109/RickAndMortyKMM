package com.rickandmortykmm.domain.repo

import com.rickandmortykmm.data.remote.dto.CharacterDto

interface ICharacterRepo {
    suspend fun getAllCharacters(): CharacterDto
}