package com.github.ebrahimi16153.topmovies.repository

import com.github.ebrahimi16153.topmovies.api.ApiServices
import com.github.ebrahimi16153.topmovies.db.FavDao
import com.github.ebrahimi16153.topmovies.models.FavoriteMovie
import com.github.ebrahimi16153.topmovies.models.ResponseOfMovieDetail
import com.github.ebrahimi16153.topmovies.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DetailRepository @Inject constructor(private val apiServices: ApiServices , private val dao: FavDao) {

    suspend fun movieDetail(id: Int): Flow<Result<ResponseOfMovieDetail>> {
        return flow {

            val getResponse = try {
                apiServices.getMovieDetail(id)

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(massage = e.message.toString()))
                return@flow
            }

            emit(Result.Success(getResponse))

        }
    }

    fun existsMovie(id:Int) = dao.existsMovie(id)
    suspend fun addFav(favoriteMovie: FavoriteMovie) = dao.insert(favoriteMovie = favoriteMovie)
    suspend fun deleteFav(favoriteMovie: FavoriteMovie) = dao.delete(favoriteMovie = favoriteMovie)



}