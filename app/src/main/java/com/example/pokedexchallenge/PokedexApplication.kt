package com.example.pokedexchallenge

import android.app.Application
import dagger.Module
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokedexApplication : Application() {
    @Override
    override fun onCreate() {
        super.onCreate()
    }
}
