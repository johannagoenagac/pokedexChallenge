package com.example.pokedexchallenge.data.repository

import com.example.pokedexchallenge.data.remote.PokeApiService
import com.example.pokedexchallenge.data.remote.model.PokemonDetailResponse
import com.example.pokedexchallenge.data.remote.model.PokemonListResponse
import com.example.pokedexchallenge.domain.repository.PokemonRepository
import com.example.pokedexchallenge.utils.safeApiCall
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonListResponse> {
        return safeApiCall { apiService.getPokemonList(limit, offset) }
    }

    override suspend fun getPokemonDetail(name: String): Result<PokemonDetailResponse> {
        return safeApiCall { apiService.getPokemonDetail(name) }
    }
}
