package com.angelfgdeveloper.moviekotlinapp.core

import java.lang.Exception

// Estados de la aplicacion en segundo plano o coroutinas
sealed class Resource<out T> {
    class Loading<out T>: Resource<T>() // Cuando esta cargando los datos de un webserver
    data class Success<out T>(val data: T): Resource<T>() // Llamada exitosa al webserver
    data class Failure(val exception: Exception): Resource<Nothing>() // Fallos en la petición
}