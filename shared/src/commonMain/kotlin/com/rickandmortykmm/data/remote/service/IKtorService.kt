package com.rickandmortykmm.data.remote.service

import com.rickandmortykmm.data.remote.dto.CharacterDto

interface IKtorService {
    suspend fun getAllCharacters(): CharacterDto
}