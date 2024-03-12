package com.github.ebrahimi16153.topmovies.repository

import com.github.ebrahimi16153.topmovies.api.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiServices: ApiServices) {

    fun mainBannerMovie(id:Int) = apiServices.minBannerMovieList(genreId = id)


}