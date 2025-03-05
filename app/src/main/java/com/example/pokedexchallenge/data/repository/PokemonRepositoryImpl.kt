package com.example.pokedexchallenge.data.repository

import com.example.pokedexchallenge.data.local.dao.PokemonDao
import com.example.pokedexchallenge.data.local.entities.PokemonEntity
import com.example.pokedexchallenge.data.local.entities.SearchQueryEntity
import com.example.pokedexchallenge.data.remote.PokeApiService
import com.example.pokedexchallenge.data.remote.model.Pokemon
import com.example.pokedexchallenge.data.remote.model.PokemonList
import com.example.pokedexchallenge.domain.repository.PokemonRepository
import com.example.pokedexchallenge.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService,
    private val pokemonDao: PokemonDao
) : PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            apiService.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error ocurred")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonDetail(name: String): Resource<Pokemon> {
        val response = try {
            apiService.getPokemonDetail(name)
        } catch (e: Exception) {
            return Resource.Error("An unknown error ocurred")
        }
        return Resource.Success(response)
    }

    override suspend fun addFavorite(pokemon: PokemonEntity) {
        pokemonDao.insertFavorite(pokemon)
    }

    override suspend fun removeFavorite(pokemon: PokemonEntity) {
        pokemonDao.deleteFavorite(pokemon)
    }

    override fun getAllFavorites(): Flow<List<PokemonEntity>> {
        return pokemonDao.getAllFavorites()
    }

    override suspend fun isFavorite(pokemonId: Int): Boolean {
        return pokemonDao.isFavorite(pokemonId)
    }

    override suspend fun saveSearchQuery(query: String) {
        val searchQuery = SearchQueryEntity(query = query)
        pokemonDao.insertSearchQuery(searchQuery)
    }

    override fun getRecentSearches(): Flow<List<SearchQueryEntity>> {
        return pokemonDao.getRecentSearches()
    }

    override suspend fun clearSearchHistory() {
        pokemonDao.clearSearchHistory()
    }
}
