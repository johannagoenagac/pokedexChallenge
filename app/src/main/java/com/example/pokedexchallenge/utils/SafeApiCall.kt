package com.example.pokedexchallenge.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            Result.success(apiCall())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
