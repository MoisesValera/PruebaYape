package com.mevalera.pruebayape.util

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    object Loading : Result<Nothing>()
    data class Error(val exception: Exception) : Result<Nothing>()
}