package com.example.pokedexchallenge.domain.usecase

import com.example.pokedexchallenge.domain.repository.PokemonRepository
import com.example.pokedexchallenge.data.remote.model.PokemonDetailResponse
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Result<PokemonDetailResponse> {
        return repository.getPokemonDetail(name)
    }
}
