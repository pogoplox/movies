package com.example.moviecompose.data

object Results {

    data class Response<T>(val response:T)

    data class ApiError(val errorMessage:String = ""): Throwable(errorMessage)

}