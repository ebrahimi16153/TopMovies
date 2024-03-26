package com.github.ebrahimi16153.topmovies.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.ebrahimi16153.topmovies.models.FavoriteMovie


@Database([FavoriteMovie::class], version = 1, exportSchema = false)
abstract class FavDataBase:RoomDatabase() {

    abstract val favDao:FavDao


}