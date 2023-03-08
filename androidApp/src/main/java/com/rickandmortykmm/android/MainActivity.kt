package com.rickandmortykmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.rickandmortykmm.android.navigation.CharactersNavHost
import com.rickandmortykmm.android.theme.CharactersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharactersTheme {
                CharactersNavHost(modifier = Modifier.fillMaxSize())
            }
        }
    }
}