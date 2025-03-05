package com.example.pokedexchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.pokedexchallenge.navigation.PokedexNavGraph
import com.example.pokedexchallenge.ui.theme.PokedexChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexChallengeTheme {
                val navController = rememberNavController()
                PokedexNavGraph(navController = navController)
            }
        }
    }
}

