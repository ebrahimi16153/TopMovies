package com.github.ebrahimi16153.topmovies.api

import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import com.github.ebrahimi16153.topmovies.models.ResponseOfMovieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("genres/{genre_id}/movies")
    suspend fun getMainBannerMovieList(@Path("genre_id") id: Int): ResponseOfMainBannerMovie

    //second way without Flow
//    @GET("genres/{genre_id}/movies")
//     suspend fun getMainBannerMovieList(@Path("genre_id") id: Int):ResponseOfMainBannerMovie


    @GET("movies")
    suspend fun getLastMovieList(@Query("page") page:Int):ResponseOfMovieList


    @GET("movies")
    suspend fun getSearchMovieList(@Query("q") searchQuery:String):ResponseOfMovieList


}