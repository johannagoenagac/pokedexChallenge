package com.example.pokedexchallenge.presentation.ui.pokemonlist

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedexchallenge.presentation.viewmodel.PokemonViewModel
import com.example.pokedexchallenge.presentation.viewmodel.PokemonUiState

@Composable
fun PokemonListScreen(navController: NavController) {
    val viewModel: PokemonViewModel = hiltViewModel()
    val pokemonListState = viewModel.pokemonListState.collectAsState()
    val favoritePokemonsState = viewModel.favoritePokemonsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = pokemonListState.value) {
            is PokemonUiState.Loading -> {
                PokemonListSkeleton()
            }

            is PokemonUiState.Success -> {
                PokemonListSuccess(
                    pokemonList = state.data,
                    favoritePokemons = favoritePokemonsState.value,
                    onPokemonClick = { pokemonName ->
                        navController.navigate("pokemonDetail/$pokemonName")
                    },
                    listState = androidx.compose.foundation.lazy.rememberLazyListState(),
                    onLoadMore = { viewModel.fetchPokemonList() }
                )
            }

            is PokemonUiState.Error -> {
                //Todo add error component
            }

            is PokemonUiState.Empty -> {
                //Todo add emptyList component
            }
        }
    }
}