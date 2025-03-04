package com.example.pokedexchallenge.domain.usecase


import com.example.pokedexchallenge.domain.repository.PokemonRepository
import com.example.pokedexchallenge.data.remote.model.PokemonListResponse
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): Result<PokemonListResponse> {
        return repository.getPokemonList(limit, offset)
    }
}
