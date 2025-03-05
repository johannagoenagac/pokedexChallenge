package com.example.pokedexchallenge.presentation.ui.pokemondetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedexchallenge.presentation.viewmodel.PokemonUiState
import com.example.pokedexchallenge.presentation.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(
    paddingValues: PaddingValues, pokemonName: String, onBack: () -> Unit) {
    val viewModel: PokemonViewModel = hiltViewModel()
    val pokemonDetailState by viewModel.pokemonDetailState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPokemonDetail(pokemonName)
    }

        when (pokemonDetailState) {
            is PokemonUiState.Loading -> {
                LoadingIndicator()
            }
            is PokemonUiState.Success -> PokemonDetailContent(
                (pokemonDetailState as PokemonUiState.Success).data
            )

            is PokemonUiState.Error -> {

            }

            PokemonUiState.Empty -> {

            }
        }
    }


