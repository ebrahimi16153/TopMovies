package com.github.ebrahimi16153.topmovies.di

import android.content.Context
import androidx.room.Room
import com.github.ebrahimi16153.topmovies.db.FavDataBase
import com.github.ebrahimi16153.topmovies.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DbModule {

    @Singleton
    @Provides
    fun provideFavDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FavDataBase::class.java, Constant.FAV_DATABASE).build()


    @Singleton
    @Provides
    fun provideFavDao(dataBase: FavDataBase) = dataBase.favDao


}