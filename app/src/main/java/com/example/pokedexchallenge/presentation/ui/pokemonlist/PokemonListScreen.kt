package com.example.pokedexchallenge.presentation.ui.pokemonlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.navigation.NavigationRoutes
import com.example.pokedexchallenge.presentation.ui.components.EmptyListScreen
import com.example.pokedexchallenge.presentation.ui.components.ErrorScreen
import com.example.pokedexchallenge.presentation.viewmodel.PokemonUiState
import com.example.pokedexchallenge.presentation.viewmodel.PokemonViewModel
import kotlinx.coroutines.launch

@Composable
fun PokemonListScreen(navController: NavController) {

    val viewModel: PokemonViewModel = hiltViewModel()
    val pokemonListState = viewModel.pokemonListState.collectAsState()
    val favoritePokemonsState = viewModel.favoritePokemonsState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.fetchPokemonList()
    }

    val errorMessage = stringResource(id = R.string.error_list_message)
    LaunchedEffect(pokemonListState.value) {
        if (pokemonListState.value is PokemonUiState.Error) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    errorMessage
                )
            }
        }
    }

        Box(modifier = Modifier
            .fillMaxSize()) {
            when (val state = pokemonListState.value) {
                is PokemonUiState.Loading -> {
                    PokemonListSkeleton()
                }

                is PokemonUiState.Success -> {
                    PokemonListSuccess(
                        pokemonList = state.data,
                        favoritePokemons = favoritePokemonsState.value,
                        onPokemonClick = { pokemonName ->
                            navController.navigate(
                                NavigationRoutes.POKEMON_DETAIL.replace(
                                    "{pokemonName}",
                                    pokemonName
                                )
                            )
                        },
                        listState = androidx.compose.foundation.lazy.rememberLazyListState(),
                        onLoadMore = { viewModel.fetchPokemonList() }
                    )
                }

                is PokemonUiState.Error -> {
                    ErrorScreen(
                        errorMessage = stringResource(id = R.string.error_list_message) ,
                        onRetry = { viewModel.retryFetchingPokemonList() })
                }

                is PokemonUiState.Empty -> {
                    EmptyListScreen(
                        text = stringResource(id = R.string.empty_pokemon_list_description)
                    )
                }
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )
        }
    }

