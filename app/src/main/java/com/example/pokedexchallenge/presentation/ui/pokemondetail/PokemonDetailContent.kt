package com.example.pokedexchallenge.presentation.ui.pokemondetail

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedexchallenge.data.remote.model.Pokemon
import com.example.pokedexchallenge.presentation.ui.components.ActionButton
import java.util.Locale
import com.example.pokedexchallenge.R

@Composable
fun PokemonDetailContent(pokemon: Pokemon) {

    val context = LocalContext.current
    var isFavorite by remember { mutableStateOf(false) }

    val imageUrls = listOfNotNull(
        pokemon.sprites.front_default,
        pokemon.sprites.back_default
    )

    val pagerState = rememberPagerState(pageCount = { imageUrls.size })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (pagerState.pageCount > 0) {
            HorizontalPager(state = pagerState) { page ->
                AsyncImage(
                    modifier = Modifier
                        .size(400.dp),
                    model = ImageRequest.Builder(context)
                        .data(imageUrls[page])
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit

                )
            }
        }

        val name = pokemon.name.capitalize(Locale.ROOT)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = name, style = MaterialTheme.typography.headlineMedium)
        Text(
            text = stringResource(id = R.string.type_text, pokemon.types.joinToString { it.type.name } ),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            ActionButton(
                text = stringResource(id = R.string.share_button_label),
                icon = Icons.Filled.Share,
                onClick = {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "Mira este Pokémon: ${pokemon.name}"
                        )
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(shareIntent, null))
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            val textButton = if (isFavorite) stringResource(id = R.string.remove_favorite_button_label)
            else stringResource(id = R.string.add_favorite_button_label)

            ActionButton(
                text = textButton,
                icon = Icons.Filled.Favorite,
                onClick = { isFavorite = !isFavorite }
            )
        }
    }
}
