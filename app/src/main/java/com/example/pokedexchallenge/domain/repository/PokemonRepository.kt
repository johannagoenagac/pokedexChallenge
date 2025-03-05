package com.example.pokedexchallenge.domain.repository

import com.example.pokedexchallenge.data.local.entities.PokemonEntity
import com.example.pokedexchallenge.data.local.entities.SearchQueryEntity
import com.example.pokedexchallenge.data.remote.model.Pokemon
import com.example.pokedexchallenge.data.remote.model.PokemonList
import com.example.pokedexchallenge.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>
    suspend fun getPokemonDetail(name: String): Resource<Pokemon>

    suspend fun addFavorite(pokemon: PokemonEntity)
    suspend fun removeFavorite(pokemon: PokemonEntity)
    fun getAllFavorites(): Flow<List<PokemonEntity>>
    suspend fun isFavorite(pokemonId: Int): Boolean

    suspend fun saveSearchQuery(query: String)
    fun getRecentSearches(): Flow<List<SearchQueryEntity>>
    suspend fun clearSearchHistory()
}
