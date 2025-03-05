package com.example.pokedexchallenge.presentation.ui.favoritespokemon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.domain.model.PokemonItem
import com.example.pokedexchallenge.presentation.ui.pokemonlist.PokemonListItem
import com.example.pokedexchallenge.presentation.viewmodel.PokemonViewModel

@Composable
fun FavoritesPokemonListScreen(navController: NavController) {
    val viewModel: PokemonViewModel = hiltViewModel()
    val favoritePokemons = viewModel.favoritePokemonsState.collectAsState().value

    if(favoritePokemons.isEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(favoritePokemons) { pokemon ->
                PokemonListItem(
                    pokemon = PokemonItem(
                        pokemonName = pokemon.name,
                        imageUrl = pokemon.imageUrl,
                        number = pokemon.id
                    ),
                    favoritePokemons = emptyList(),
                    onPokemonClick = { navController.navigate("pokemonDetail/${pokemon.name}") }
                )
            }
        }
    }else{

    }


}

@Composable
fun EmptyfavoritesPokemonListScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.empty_list_description),
            fontSize = 18.sp
        )
    }
}