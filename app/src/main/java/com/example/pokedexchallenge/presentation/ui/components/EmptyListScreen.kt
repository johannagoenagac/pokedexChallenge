package com.example.pokedexchallenge.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexchallenge.R

@Composable
fun EmptyListScreen(
    modifier: Modifier = Modifier,
    text: String,
    onRetry: () -> Unit = {},
    onFinish: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(50.dp))

        PrimaryButton(
            text = stringResource(id = R.string.retry_button_label),
            onClick = { onRetry() })
        OutlinedButton(
            text = stringResource(id = R.string.exit_button_label),
            onClick = { onFinish() })

    }
}
