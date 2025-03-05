package com.example.pokedexchallenge.presentation.ui.pokemonlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedexchallenge.utils.ExtensionFunctions.shimmerEffect
import com.example.pokedexchallenge.utils.constants.Constants.MAX_ITEMS_SKELETON

@Composable
fun PokemonListSkeleton() {
    Column(modifier = Modifier
        .fillMaxSize()) {
        repeat(MAX_ITEMS_SKELETON) {
            PokemonListItemSkeleton()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PokemonListItemSkeleton() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .shimmerEffect()
            )
        }
    }
}
