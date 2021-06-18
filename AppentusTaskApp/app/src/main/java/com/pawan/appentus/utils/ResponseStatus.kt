package com.pawan.appentus.utils

sealed class ResponseStatus<out T> {
    data class Success<T>(val response: T) : ResponseStatus<T>()
    data class Error(val errorMessage: String, val errorCode: Int = -1): ResponseStatus<Nothing>()
    data class Loader(val isLoading: Boolean) : ResponseStatus<Nothing>()
}