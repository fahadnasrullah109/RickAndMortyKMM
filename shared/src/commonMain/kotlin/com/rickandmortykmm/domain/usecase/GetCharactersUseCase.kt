package com.rickandmortykmm.domain.usecase

import com.rickandmortykmm.data.remote.dto.asDomainModel
import com.rickandmortykmm.domain.repo.ICharacterRepo
import kotlinx.coroutines.flow.flow

class GetCharactersUseCase(private val repository: ICharacterRepo) {

    operator fun invoke() = flow {
        emit(repository.getAllCharacters().asDomainModel())
    }
}