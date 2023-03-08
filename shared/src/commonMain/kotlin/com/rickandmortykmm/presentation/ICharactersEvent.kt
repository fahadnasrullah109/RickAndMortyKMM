package com.rickandmortykmm.presentation

sealed interface ICharactersEvent {
    object GetAllCharacters : ICharactersEvent
}