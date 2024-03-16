package com.github.ebrahimi16153.topmovies.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.ebrahimi16153.topmovies.util.Constant


@Entity(Constant.FAV_TABLE)
data class FavoriteMovie(
    @PrimaryKey
    var id :Int = 0,
    var poster:String ="",
    var title:String ="",
    var rate:String ="",
    var country:String ="",
    var year:String =""
)