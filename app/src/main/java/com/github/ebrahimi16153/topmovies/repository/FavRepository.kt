package com.github.ebrahimi16153.topmovies.repository

import com.github.ebrahimi16153.topmovies.db.FavDao
import com.github.ebrahimi16153.topmovies.models.FavoriteMovie
import javax.inject.Inject

class FavRepository @Inject constructor(private val dao: FavDao) {

    fun getFavList() = dao.getFavList()
}