package com.longph.mynews.data.remote

sealed class ApiResponse<out T : Any> {
    data class Success<T : Any>(val result: T?) : ApiResponse<T>()
    data class Error(val errorCode: Int, val errorMessage: String) : ApiResponse<Nothing>()
    data class Exception(val exception: kotlin.Exception) : ApiResponse<Nothing>()
}