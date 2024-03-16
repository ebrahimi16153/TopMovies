package com.github.ebrahimi16153.topmovies.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.ebrahimi16153.topmovies.models.FavoriteMovie
import com.github.ebrahimi16153.topmovies.util.Constant
import kotlinx.coroutines.flow.Flow


@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteMovie:FavoriteMovie)

    @Delete
    suspend fun delete(favoriteMovie: FavoriteMovie)

    @Query("SELECT * FROM ${Constant.FAV_TABLE}")
    fun getFavList():Flow<List<FavoriteMovie>>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constant.FAV_TABLE} WHERE id =:movieId)")
    fun existsMovie(movieId:Int):Flow<Boolean>


}