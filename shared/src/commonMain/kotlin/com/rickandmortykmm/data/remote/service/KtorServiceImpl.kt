package com.rickandmortykmm.data.remote.service

import com.rickandmortykmm.data.remote.dto.CharacterDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class KtorServiceImpl(
    private val httpClient: HttpClient, private val baseUrl: String
) : IKtorService {
    override suspend fun getAllCharacters(): CharacterDto =
        httpClient.get("$baseUrl/${EndPoints.CHARACTERS}").body()
}