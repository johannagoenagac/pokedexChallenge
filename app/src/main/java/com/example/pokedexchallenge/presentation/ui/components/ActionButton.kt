package com.example.pokedexchallenge.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(imageVector = icon, contentDescription = text)
        }
    }
}