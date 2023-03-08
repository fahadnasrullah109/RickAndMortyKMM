package com.rickandmortykmm.android.ui.characters

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.rickandmortykmm.android.R
import com.rickandmortykmm.android.components.AppTopBar
import com.rickandmortykmm.android.components.ErrorComponent
import com.rickandmortykmm.android.components.Loading
import com.rickandmortykmm.domain.models.CharacterDomainModel
import com.rickandmortykmm.presentation.CharactersViewModel
import com.rickandmortykmm.presentation.ICharactersEvent
import com.rickandmortykmm.presentation.ICharactersUiState
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen(onCharacterSelected : (CharacterDomainModel) -> Unit,
    viewModel: CharactersViewModel = getViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.onEvent(ICharactersEvent.GetAllCharacters)
    }
    val state by viewModel.state.collectAsState()
    CharactersContent(state = state, modifier = Modifier.fillMaxSize()) {
        onCharacterSelected(it)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun CharactersContent(state: ICharactersUiState, modifier: Modifier = Modifier, onCharacterSelected : (CharacterDomainModel) -> Unit) {
    Scaffold(modifier = modifier, topBar = {
        AppTopBar(title = stringResource(id = R.string.characters))
    }) { innerPadding ->
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        when (state) {
            is ICharactersUiState.Error -> {
                ErrorComponent(message = state.errorMessage, modifier = modifier)
            }
            ICharactersUiState.Idle -> {}
            ICharactersUiState.Loading -> {
                Loading(modifier = modifier)
            }
            is ICharactersUiState.Success -> {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .consumedWindowInsets(innerPadding)
                ) {
                    charactersItems(state.characters) {
                        onCharacterSelected(it)
                    }
                }
            }
        }
    }
}

fun LazyListScope.charactersItems(characters: List<CharacterDomainModel>, onCharacterSelected : (CharacterDomainModel) -> Unit) {
    items(characters) { item ->
        CharacterCard(character = item, modifier = Modifier.clickable {
            onCharacterSelected(item)
        })
    }
}

@Composable
fun CharacterCard(
    character: CharacterDomainModel, modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .height(280.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column {
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                model = ImageRequest.Builder(
                    LocalContext.current
                ).scale(Scale.FILL).data(character.image).crossfade(true).build(),
                contentDescription = character.name,
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = character.status,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = character.species, style = MaterialTheme.typography.bodySmall.copy(
                        MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
