package com.github.ebrahimi16153.topmovies.repository

import com.github.ebrahimi16153.topmovies.api.ApiServices
import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import com.github.ebrahimi16153.topmovies.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

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


    //second way without Flow
//    suspend fun mainBanner(id:Int) = apiServices.getMainBannerMovieList(id)


}