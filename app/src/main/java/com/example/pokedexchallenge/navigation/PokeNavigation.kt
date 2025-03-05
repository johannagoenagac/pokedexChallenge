package com.example.pokedexchallenge.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pokedexchallenge.presentation.ui.pokemondetail.PokemonDetailScreen
import com.example.pokedexchallenge.presentation.ui.pokemonlist.PokemonListScreen

@Composable
fun PokedexNavGraph(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "pokemonList"
    val appBarTitle = when (currentRoute) {
        //Todo Fix
        "pokemonList" -> "Pokedex"
        "pokemonDetail/{pokemonName}" -> "Detalle del Pokémon"
        else -> "Pokedex"
    }
    Scaffold(
        topBar = {
            AppBar(
                title = appBarTitle,
                showBackButton = currentRoute != "pokemonList",
                onBack = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        //Todo create routes
        NavHost(
            navController = navController,
            startDestination = "pokemonList",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("pokemonList") {
                PokemonListScreen(navController = navController)
            }
            composable("pokemonDetail/{pokemonName}") { backStackEntry ->
                val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
                PokemonDetailScreen( paddingValues = paddingValues, pokemonName = pokemonName) {
                    navController.popBackStack()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, showBackButton: Boolean = false, onBack: (() -> Unit)? = null) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBack ?: {}) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                }
            } else null
        }
    )
}
