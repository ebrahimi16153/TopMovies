package com.github.ebrahimi16153.topmovies.models

sealed class Result<out T>(val data: T? = null) {


    class Success<T>(data: T? = null):Result<T>(data)
    data class Error(val massage: String):Result<Nothing>()

}