package com.example.pokedexchallenge.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedexchallenge.R

@Composable
fun ErrorScreen(
    errorMessage: String,
    onRetry: () -> Unit,
    showExitButton: Boolean = false,
    onExit: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.lucha),
            contentDescription = "Error"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = errorMessage,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.weight(1f))
        PrimaryButton(
            stringResource(id = R.string.retry_button_label),
            onClick = onRetry
        )
        if (showExitButton) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = { onExit?.invoke() },
                text = stringResource(id = R.string.exit_button_label),
            )
        }

    }
}

