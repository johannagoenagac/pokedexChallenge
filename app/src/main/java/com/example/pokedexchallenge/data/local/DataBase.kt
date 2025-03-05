package com.example.pokedexchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedexchallenge.data.local.dao.PokemonDao
import com.example.pokedexchallenge.data.local.entities.PokemonEntity
import com.example.pokedexchallenge.data.local.entities.SearchQueryEntity

@Database(entities = [PokemonEntity::class, SearchQueryEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
