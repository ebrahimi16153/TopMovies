package com.github.ebrahimi16153.topmovies.api

import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("genres/{genre_id}/movies")
    suspend fun getMainBannerMovieList(@Path("genre_id") id: Int): ResponseOfMainBannerMovie

    //second way without Flow
//    @GET("genres/{genre_id}/movies")
//     suspend fun getMainBannerMovieList(@Path("genre_id") id: Int):ResponseOfMainBannerMovie


}