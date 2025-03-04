package com.example.pokedexchallenge.data.remote.dtos

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)