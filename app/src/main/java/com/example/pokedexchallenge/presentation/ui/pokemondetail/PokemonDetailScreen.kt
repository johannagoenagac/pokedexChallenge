package com.example.pokedexchallenge.presentation.ui.pokemondetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.presentation.ui.components.ErrorScreen
import com.example.pokedexchallenge.presentation.viewmodel.PokemonUiState
import com.example.pokedexchallenge.presentation.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(
    navController: NavController,
    pokemonName: String
) {
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
            ErrorScreen(
                errorMessage = stringResource(id = R.string.error_detail_description),
                onRetry = { viewModel.fetchPokemonDetail(pokemonName) },
                showExitButton = true,
                onExit = { navController.popBackStack() }
            )
        }

        PokemonUiState.Empty -> {}


    }
}


