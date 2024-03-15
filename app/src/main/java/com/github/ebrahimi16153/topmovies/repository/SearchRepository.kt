package com.github.ebrahimi16153.topmovies.repository

import com.github.ebrahimi16153.topmovies.api.ApiServices
import com.github.ebrahimi16153.topmovies.models.ResponseOfMovieList
import com.github.ebrahimi16153.topmovies.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun searchMovie(q: String): Flow<Result<ResponseOfMovieList>> {

        return flow {
            val getResponse = try {
                apiServices.getSearchMovieList(q)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(massage = e.message.toString()))
                return@flow
            }
            emit(Result.Success(getResponse))
        }

    }


}