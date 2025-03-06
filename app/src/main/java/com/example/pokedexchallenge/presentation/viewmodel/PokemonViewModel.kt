package com.example.pokedexchallenge.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexchallenge.data.local.entities.PokemonEntity
import com.example.pokedexchallenge.data.remote.model.Pokemon
import com.example.pokedexchallenge.domain.model.PokemonItem
import com.example.pokedexchallenge.domain.repository.PokemonRepository
import com.example.pokedexchallenge.domain.usecase.GetPokemonDetailUseCase
import com.example.pokedexchallenge.domain.usecase.GetPokemonListUseCase
import com.example.pokedexchallenge.utils.Resource
import com.example.pokedexchallenge.utils.constants.Constants.IMAGE_BASE_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonListState =
        MutableStateFlow<PokemonUiState<List<PokemonItem>>>(PokemonUiState.Loading)
    val pokemonListState: StateFlow<PokemonUiState<List<PokemonItem>>> =
        _pokemonListState.asStateFlow()

    private val _pokemonDetailState =
        MutableStateFlow<PokemonUiState<Pokemon>>(PokemonUiState.Loading)
    val pokemonDetailState: StateFlow<PokemonUiState<Pokemon>> = _pokemonDetailState.asStateFlow()

    private val _favoritePokemonsState =
        MutableStateFlow<List<PokemonEntity>>(emptyList())
    val favoritePokemonsState: StateFlow<List<PokemonEntity>> = _favoritePokemonsState

    private var currentOffset = 0
    private val pageSize = 20
    private val allPokemonList: MutableList<PokemonItem> = mutableListOf()

    var endReached = mutableStateOf(false)
        private set
    var isLoading = mutableStateOf(false)
        private set


    init {
        loadFavorites()
        fetchPokemonList()
    }

    fun fetchPokemonList() {
        if (isLoading.value || endReached.value) return

        isLoading.value = true
        viewModelScope.launch {
            getPokemonListUseCase(limit = pageSize, offset = currentOffset).collect { result ->
                when (result) {
                    is Resource.Loading -> _pokemonListState.value = PokemonUiState.Loading
                    is Resource.Success -> {
                        result.data?.let { data ->
                            val newPokemonList = data.results.mapIndexed { index, pokemon ->
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

                                endReached.value = allPokemonList.size >= data.count
                            }
                        }
                    }
                    is Resource.Error -> {
                        _pokemonListState.value = PokemonUiState.Error(result.message!!)
                    }
                }
                isLoading.value = false
            }
        }
    }
    fun retryFetchingPokemonList() {
        viewModelScope.launch {
            _pokemonListState.value = PokemonUiState.Loading
            fetchPokemonList()
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
                            PokemonUiState.Error("Detalles del Pokémon no disponible")
                    }

                    is Resource.Error -> _pokemonDetailState.value =
                        PokemonUiState.Error(result.message!!)
                }
            }
        }
    }

    fun addFavorite(pokemon: PokemonEntity) {
        viewModelScope.launch {
            pokemonRepository.addFavorite(pokemon)
            loadFavorites()
        }
    }

    fun removeFavorite(pokemon: PokemonEntity) {
        viewModelScope.launch {
            pokemonRepository.removeFavorite(pokemon)
            loadFavorites()
        }
    }

    suspend fun isFavorite(pokemonId: Int): Boolean {
        return pokemonRepository.isFavorite(pokemonId)
    }


    fun loadFavorites() {
        viewModelScope.launch {
            pokemonRepository.getAllFavorites().collect { favorites ->
                _favoritePokemonsState.value = favorites
            }
        }
    }

    fun saveSearchQuery(query: String) {
        viewModelScope.launch {
            pokemonRepository.saveSearchQuery(query)
        }
    }

    fun getRecentSearches() {
        viewModelScope.launch {
            pokemonRepository.getRecentSearches().collect { searches ->
                //todo search manage
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
