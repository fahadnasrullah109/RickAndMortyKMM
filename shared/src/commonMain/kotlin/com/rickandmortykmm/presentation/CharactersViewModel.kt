package com.rickandmortykmm.presentation

import com.rickandmortykmm.data.remote.Result
import com.rickandmortykmm.data.remote.asResult
import com.rickandmortykmm.domain.usecase.GetCharactersUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {
    private val _state = MutableStateFlow<ICharactersUiState>(ICharactersUiState.Idle)
    val state = _state.asStateFlow()

    fun onEvent(event: ICharactersEvent) {
        when (event) {
            ICharactersEvent.GetAllCharacters -> getCharacters()
        }
    }

    private fun getCharacters() {
        viewModelScope.launch {
            getCharactersUseCase.invoke().asResult().collectLatest { result ->
                when (result) {
                    Result.Loading -> {
                        _state.update { ICharactersUiState.Loading }
                    }
                    Result.Idle -> {
                        _state.update {
                            ICharactersUiState.Idle
                        }
                    }
                    is Result.Success -> {
                        _state.update {
                            ICharactersUiState.Success(result.data)
                        }
                    }
                    is Result.Error -> {
                        _state.update {
                            ICharactersUiState.Error(result.exception.message)
                        }
                    }
                }
            }
        }
    }

}