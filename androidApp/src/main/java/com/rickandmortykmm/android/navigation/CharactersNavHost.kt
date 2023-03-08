package com.rickandmortykmm.android.navigation

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rickandmortykmm.android.ui.characters.CharactersDetailScreen
import com.rickandmortykmm.android.ui.characters.CharactersScreen
import com.rickandmortykmm.domain.models.CharacterDomainModel

@Composable
fun CharactersNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.CharactersListing.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        charactersListGraph(navController)
        characterDetailScreen(navController)
    }
}

fun NavGraphBuilder.charactersListGraph(
    navController: NavController
) {
    composable(Destinations.CharactersListing.route) {
        CharactersScreen(onCharacterSelected = { character ->
            navController.navigate(
                route = Destinations.CharacterDetail().route,
                args = bundleOf(Destinations.CharacterDetail().character to character)
            )
        })
    }
}

fun NavGraphBuilder.characterDetailScreen(navController: NavController) {
    composable(
        route = Destinations.CharacterDetail().route,
    ) { entry ->
        val character =
            entry.parcelableData<CharacterDomainModel>(Destinations.CharacterDetail().character)
        character?.let {
            CharactersDetailScreen(
                character = character,
                modifier = Modifier.fillMaxSize(),
                navigateBack = {
                    navController.navigateUp()
                })
        }
    }
}

fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val routeLink =
        NavDeepLinkRequest.Builder.fromUri(NavDestination.createRoute(route).toUri()).build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}

inline fun <T> NavBackStackEntry.parcelableData(key: String): T? {
    return arguments?.getParcelable(key) as? T
}