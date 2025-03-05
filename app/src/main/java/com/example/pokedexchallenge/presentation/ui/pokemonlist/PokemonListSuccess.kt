package com.example.pokedexchallenge.presentation.ui.pokemonlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.pokedexchallenge.domain.model.PokemonItem
import java.util.Locale

@Composable
fun PokemonListSuccess(
    pokemonList: List<PokemonItem>,
    onPokemonClick: (String) -> Unit,
    listState: androidx.compose.foundation.lazy.LazyListState,
    onLoadMore: () -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
        items(pokemonList) { pokemon ->
            PokemonListItem(pokemon, onPokemonClick)
        }

        item {
            LaunchedEffect(listState.isScrollInProgress) {
                if (!listState.isScrollInProgress && listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == pokemonList.lastIndex) {
                    onLoadMore()
                }
            }
        }
    }
}


@Composable
fun PokemonListItem(
    pokemon: PokemonItem,
    onPokemonClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onPokemonClick(pokemon.pokemonName) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.pokemonName,
                    modifier = Modifier.size(64.dp),
                    placeholder = rememberAsyncImagePainter("https://via.placeholder.com/64"),
                    error = rememberAsyncImagePainter("https://via.placeholder.com/64")
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = pokemon.pokemonName.capitalize(Locale.ROOT),
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "")
        }
    }
}