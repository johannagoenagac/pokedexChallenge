package com.example.pokedexchallenge.domain.repository

import com.example.pokedexchallenge.data.remote.model.Pokemon
import com.example.pokedexchallenge.data.remote.model.PokemonList
import com.example.pokedexchallenge.utils.Resource

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>
    suspend fun getPokemonDetail(name: String): Resource<Pokemon>
}
