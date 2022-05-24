package com.cloud.myphoto.network

sealed class ApiResponseState<out T> {
    data class ApiException<T : Any>(val e: Throwable) : ApiResponseState<T>()
    data class Error(val code: Int, val message: String?) : ApiResponseState<Nothing>()
    data class Success<T>(val item: T) : ApiResponseState<T>()
}
