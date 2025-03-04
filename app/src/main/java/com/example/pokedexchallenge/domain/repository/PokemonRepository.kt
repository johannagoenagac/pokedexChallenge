package com.example.pokedexchallenge.domain.repository

import com.example.pokedexchallenge.data.remote.model.PokemonDetailResponse
import com.example.pokedexchallenge.data.remote.model.PokemonListResponse

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Result<PokemonListResponse>
    suspend fun getPokemonDetail(name: String): Result<PokemonDetailResponse>
}
