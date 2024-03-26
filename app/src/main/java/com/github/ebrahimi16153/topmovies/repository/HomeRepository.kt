package com.github.ebrahimi16153.topmovies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.ebrahimi16153.topmovies.api.ApiServices
import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import com.github.ebrahimi16153.topmovies.models.ResponseOfMovieList
import com.github.ebrahimi16153.topmovies.models.Result
import com.github.ebrahimi16153.topmovies.paging.MovieListPaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.Exception

class HomeRepository @Inject constructor(private val apiServices: ApiServices) {


    //first way with Flow
    suspend fun mainBannerMovie(id: Int): Flow<Result<ResponseOfMainBannerMovie>> {
        return flow {

            val getResponse = try {
                apiServices.getMainBannerMovieList(id)

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(massage = e.message.toString()))
                return@flow
            }

            emit(Result.Success(getResponse))

        }
    }


    fun lastMovie(): Flow<PagingData<ResponseOfMovieList.Data>> {

        return Pager(config = PagingConfig(pageSize = 1), pagingSourceFactory = {
            MovieListPaging(apiServices = apiServices)
        }).flow

    }

}