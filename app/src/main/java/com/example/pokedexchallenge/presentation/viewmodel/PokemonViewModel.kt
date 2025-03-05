package com.example.pokedexchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexchallenge.domain.model.PokemonItem
import com.example.pokedexchallenge.data.remote.model.Pokemon
import com.example.pokedexchallenge.domain.usecase.GetPokemonListUseCase
import com.example.pokedexchallenge.domain.usecase.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.pokedexchallenge.utils.Resource
import com.example.pokedexchallenge.utils.constants.Constants.IMAGE_BASE_URL

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _pokemonListState =
        MutableStateFlow<PokemonUiState<List<PokemonItem>>>(PokemonUiState.Loading)
    val pokemonListState: StateFlow<PokemonUiState<List<PokemonItem>>> =
        _pokemonListState.asStateFlow()

    private val _pokemonDetailState =
        MutableStateFlow<PokemonUiState<Pokemon>>(PokemonUiState.Loading)
    val pokemonDetailState: StateFlow<PokemonUiState<Pokemon>> = _pokemonDetailState.asStateFlow()

    private var currentOffset = 0
    private val pageSize = 20


    private var allPokemonList: MutableList<PokemonItem> = mutableListOf()

    fun fetchPokemonList() {
        viewModelScope.launch {
            if (allPokemonList.isNotEmpty()) {
                _pokemonListState.value = PokemonUiState.Success(allPokemonList)
                return@launch
            }

            getPokemonListUseCase(limit = pageSize, offset = currentOffset).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _pokemonListState.value = PokemonUiState.Loading
                    }
                    is Resource.Success -> {
                        val newPokemonList =
                            result.data?.results.orEmpty().mapIndexed { index, pokemon ->
                                val number = currentOffset + index + 1
                                PokemonItem(
                                    pokemonName = pokemon.name,
                                    imageUrl = "$IMAGE_BASE_URL$number.png",
                                    number = number
                                )
                            }

                        if (newPokemonList.isEmpty()) {
                            _pokemonListState.value = PokemonUiState.Empty
                        } else {
                            allPokemonList.addAll(newPokemonList)
                            _pokemonListState.value = PokemonUiState.Success(allPokemonList)
                            currentOffset += pageSize
                        }
                    }

                    is Resource.Error -> {
                        _pokemonListState.value = PokemonUiState.Error(result.message!!)
                    }
                }
            }
        }
    }

    fun fetchPokemonDetail(name: String) {
        viewModelScope.launch {
            getPokemonDetailUseCase(name).collect { result ->
                when (result) {
                    is Resource.Loading -> _pokemonDetailState.value = PokemonUiState.Loading
                    is Resource.Success -> result.data?.let {
                        _pokemonDetailState.value = PokemonUiState.Success(it)
                    } ?: run {
                        _pokemonDetailState.value =
                            PokemonUiState.Error("Datos del Pokémon no disponibles")
                    }

                    is Resource.Error -> _pokemonDetailState.value =
                        PokemonUiState.Error(result.message!!)
                }
            }
        }
    }

}

sealed class PokemonUiState<out T> {
    object Loading : PokemonUiState<Nothing>()
    object Empty : PokemonUiState<Nothing>()
    data class Success<T>(val data: T) : PokemonUiState<T>()
    data class Error(val message: String) : PokemonUiState<Nothing>()
}
