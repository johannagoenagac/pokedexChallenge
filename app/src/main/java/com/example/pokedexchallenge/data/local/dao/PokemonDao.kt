package com.example.pokedexchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedexchallenge.data.local.entities.PokemonEntity
import com.example.pokedexchallenge.data.local.entities.SearchQueryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(pokemon: PokemonEntity)

    @Delete
    suspend fun deleteFavorite(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemon_favorites")
    fun getAllFavorites(): Flow<List<PokemonEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM pokemon_favorites WHERE id = :pokemonId)")
    suspend fun isFavorite(pokemonId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchQuery(searchQuery: SearchQueryEntity)

    @Query("SELECT * FROM search_queries ORDER BY timestamp DESC LIMIT 10")
    fun getRecentSearches(): Flow<List<SearchQueryEntity>>

    @Query("DELETE FROM search_queries")
    suspend fun clearSearchHistory()
}
