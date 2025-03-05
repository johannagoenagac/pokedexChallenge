package com.example.pokedexchallenge.data.remote.model

import com.example.pokedexchallenge.data.remote.dtos.Result
import com.example.pokedexchallenge.domain.model.PokemonItem
import com.example.pokedexchallenge.utils.constants.Constants

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)
