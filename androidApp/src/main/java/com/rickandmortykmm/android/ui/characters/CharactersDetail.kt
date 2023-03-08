package com.rickandmortykmm.android.ui.characters

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.rickandmortykmm.domain.models.CharacterDomainModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersDetailScreen(
    character: CharacterDomainModel, navigateBack: () -> Unit, modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier, topBar = {
        AppTopBar(title = character.name, navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(
                        R.string.back
                    ),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        })
    }) { innerPadding ->
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        Column(modifier = modifier.padding(innerPadding)) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                model = ImageRequest.Builder(
                    LocalContext.current
                ).scale(Scale.FILL).data(character.image).crossfade(true).build(),
                contentDescription = character.name,
            )
            Column(modifier = modifier.padding(16.dp)) {
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