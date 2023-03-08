package com.rickandmortykmm.android.navigation

sealed class Destinations(val route: String) {
    object CharactersListing : Destinations("characters")
    data class CharacterDetail(val character: String = "character") :
        Destinations("character-detail")
}
