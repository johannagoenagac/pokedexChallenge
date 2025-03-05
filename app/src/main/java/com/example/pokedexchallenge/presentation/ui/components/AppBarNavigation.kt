package com.example.pokedexchallenge.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.pokedexchallenge.navigation.NavigationRoutes.FAVORITES_POKEMON_LIST
import com.example.pokedexchallenge.navigation.NavigationRoutes.POKEMON_DETAIL
import com.example.pokedexchallenge.navigation.NavigationRoutes.POKEMON_HOME_LIST


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentRoute: String,
    onBack: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val title = when (currentRoute) {
        POKEMON_HOME_LIST -> "Pokedex"
        POKEMON_DETAIL -> "Detalle"
        FAVORITES_POKEMON_LIST -> "Favoritos"
        else -> "Pokedex"
    }

    val showBackButton = currentRoute != POKEMON_HOME_LIST
    val showFavoriteButton = currentRoute != FAVORITES_POKEMON_LIST

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                }
            }
        },
        actions = {
            if (showFavoriteButton) {
                IconButton(onClick = onFavoriteClick) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favoritos")
                }
            }
        }
    )
}
