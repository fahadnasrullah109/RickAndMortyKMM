package com.rickandmortykmm.presentation

import com.rickandmortykmm.domain.models.CharacterDomainModel


sealed interface ICharactersUiState {
    object Idle : ICharactersUiState
    object Loading : ICharactersUiState
    data class Success(val characters: List<CharacterDomainModel>) : ICharactersUiState
    data class Error(val errorMessage: String) : ICharactersUiState
}