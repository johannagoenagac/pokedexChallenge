package com.example.pokedexchallenge.utils

import retrofit2.HttpException
import java.io.IOException

object ErrorHandler {
    fun getErrorMessage(exception: Throwable): String {
        return when (exception) {
            is HttpException -> "Error en la respuesta del servidor: ${exception.code()}"
            is IOException -> "Error de conexión. Verifica tu conexión a Internet."
            else -> "Ocurrió un error inesperado: ${exception.localizedMessage ?: "Desconocido"}"
        }
    }
}
