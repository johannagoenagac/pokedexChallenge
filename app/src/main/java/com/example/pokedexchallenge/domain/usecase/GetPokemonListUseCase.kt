package com.example.pokedexchallenge.domain.usecase


import com.example.pokedexchallenge.domain.repository.PokemonRepository
import com.example.pokedexchallenge.data.remote.model.PokemonList
import com.example.pokedexchallenge.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(limit: Int, offset: Int): Flow<Resource<PokemonList>> = flow {
        emit(Resource.Loading())
        try {
            val result = repository.getPokemonList(limit, offset)
            emit(result)
        } catch (e: Exception) {
            emit(Resource.Error("Error al obtener la lista de Pokémon: ${e.message}"))
        }
    }
}
