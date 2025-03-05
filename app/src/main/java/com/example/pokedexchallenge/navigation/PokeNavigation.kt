package com.example.pokedexchallenge.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.pokedexchallenge.navigation.NavigationRoutes.FAVORITES_POKEMON_LIST
import com.example.pokedexchallenge.navigation.NavigationRoutes.POKEMON_DETAIL
import com.example.pokedexchallenge.navigation.NavigationRoutes.POKEMON_HOME_LIST
import com.example.pokedexchallenge.presentation.ui.components.AppBar
import com.example.pokedexchallenge.presentation.ui.favoritespokemon.FavoritesPokemonListScreen
import com.example.pokedexchallenge.presentation.ui.pokemondetail.PokemonDetailScreen
import com.example.pokedexchallenge.presentation.ui.pokemonlist.PokemonListScreen

@Composable
fun PokedexNavGraph(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: POKEMON_HOME_LIST

    Scaffold(
        topBar = {
            AppBar(
                currentRoute = currentRoute,
                onBack = { navController.popBackStack() },
                onFavoriteClick = { navController.navigate(FAVORITES_POKEMON_LIST) }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = POKEMON_HOME_LIST,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(POKEMON_HOME_LIST) {
                PokemonListScreen(navController = navController)
            }
            composable(POKEMON_DETAIL) { backStackEntry ->
                val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
                PokemonDetailScreen(
                    navController = navController,pokemonName = pokemonName)
            }
            composable(FAVORITES_POKEMON_LIST) {
                FavoritesPokemonListScreen(navController = navController)
            }
        }
    }
}
