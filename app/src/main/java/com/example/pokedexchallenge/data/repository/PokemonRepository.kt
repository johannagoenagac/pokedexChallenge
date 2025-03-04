package com.example.pokedexchallenge.data.repository

import com.example.pokedexchallenge.data.remote.PokeApiService
import com.example.pokedexchallenge.data.remote.model.PokemonDetailResponse
import com.example.pokedexchallenge.data.remote.model.PokemonListResponse
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val apiService: PokeApiService
) {
    suspend fun getPokemonList(limit: Int, offset: Int): PokemonListResponse {
        return apiService.getPokemonList(limit, offset)
    }

    suspend fun getPokemonDetail(name: String): PokemonDetailResponse {
        return apiService.getPokemonDetail(name)
    }
}
