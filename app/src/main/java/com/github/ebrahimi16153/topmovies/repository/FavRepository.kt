package com.github.ebrahimi16153.topmovies.repository

import com.github.ebrahimi16153.topmovies.db.FavDao
import com.github.ebrahimi16153.topmovies.models.FavoriteMovie
import javax.inject.Inject

class FavRepository @Inject constructor(private val dao: FavDao) {

    fun getFavList() = dao.getFavList()
    fun existsMovie(id:Int) = dao.existsMovie(id)
    suspend fun addFav(favoriteMovie: FavoriteMovie) = dao.insert(favoriteMovie = favoriteMovie)
    suspend fun deleteFav(favoriteMovie: FavoriteMovie) = dao.delete(favoriteMovie = favoriteMovie)

}