package com.example.pokedexchallenge.data.remote.dtos

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)