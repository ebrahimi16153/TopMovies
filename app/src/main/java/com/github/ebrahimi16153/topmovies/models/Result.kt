package com.github.ebrahimi16153.topmovies.models

sealed class Result<T> (val data:T? =null, massage:String? = null) {


    class Success<T>(data: T? = null):Result<T>(data)
    class Error<T>(data: T? = null,massage: String):Result<T>(data, massage)

}