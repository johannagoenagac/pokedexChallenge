package com.example.pokedexchallenge.domain.usecase

import com.example.pokedexchallenge.data.remote.model.Pokemon
import com.example.pokedexchallenge.domain.repository.PokemonRepository
import com.example.pokedexchallenge.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(name: String): Flow<Resource<Pokemon>> = flow {
        emit(Resource.Loading())
        emit(repository.getPokemonDetail(name))
    }
}
