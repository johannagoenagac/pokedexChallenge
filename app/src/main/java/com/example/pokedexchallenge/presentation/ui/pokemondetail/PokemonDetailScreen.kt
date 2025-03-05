package com.example.pokedexchallenge.presentation.ui.pokemondetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedexchallenge.presentation.viewmodel.PokemonUiState
import com.example.pokedexchallenge.presentation.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(
    pokemonName: String
) {
    val viewModel: PokemonViewModel = hiltViewModel()
    val pokemonDetailState by viewModel.pokemonDetailState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPokemonDetail(pokemonName)
    }

    when (pokemonDetailState) {
        is PokemonUiState.Loading -> {
            //Todo add loading
        }

        is PokemonUiState.Success -> PokemonDetailContent(
            (pokemonDetailState as PokemonUiState.Success).data
        )

        is PokemonUiState.Error -> {
            //Todo error component
        }

        PokemonUiState.Empty -> {}
    }
}


