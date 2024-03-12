package com.github.ebrahimi16153.topmovies.api

import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("genres/{genre_id}/movies")
    fun minBannerMovieList(@Path("genre_id") genreId:Int) :Flow<ResponseOfMainBannerMovie>


}