package com.example.pokedexchallenge.data.remote.model

import com.example.pokedexchallenge.data.remote.dtos.Ability
import com.example.pokedexchallenge.data.remote.dtos.Cries
import com.example.pokedexchallenge.data.remote.dtos.Form
import com.example.pokedexchallenge.data.remote.dtos.GameIndice
import com.example.pokedexchallenge.data.remote.dtos.HeldItem
import com.example.pokedexchallenge.data.remote.dtos.Move
import com.example.pokedexchallenge.data.remote.dtos.Species
import com.example.pokedexchallenge.data.remote.dtos.Sprites
import com.example.pokedexchallenge.data.remote.dtos.Stat
import com.example.pokedexchallenge.data.remote.dtos.Type

data class Pokemon(
    val abilities: List<Ability>,
    val base_experience: Int,
    val cries: Cries,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<HeldItem>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_abilities: List<Any?>,
    val past_types: List<Any?>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)
