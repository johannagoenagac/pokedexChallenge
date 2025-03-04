package com.example.pokedexchallenge.data.remote.model

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<PokemonType>
)

data class Sprites(
    val other: OtherSprites
)

data class OtherSprites(
    val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    val frontDefault: String
)

data class PokemonType(
    val type: TypeDetail
)

data class TypeDetail(
    val name: String
)


