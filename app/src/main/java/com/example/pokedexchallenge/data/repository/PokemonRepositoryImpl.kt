package com.example.pokedexchallenge.data.repository

import com.example.pokedexchallenge.data.remote.PokeApiService
import com.example.pokedexchallenge.data.remote.model.Pokemon
import com.example.pokedexchallenge.data.remote.model.PokemonList
import com.example.pokedexchallenge.domain.repository.PokemonRepository
import com.example.pokedexchallenge.utils.Resource
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService
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
}
