package com.oscar.themovieapp.data.data_local.injection

import android.content.Context
import androidx.room.Room
import com.oscar.themovieapp.data.data_local.db.MovieDao
import com.oscar.themovieapp.data.data_local.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "my-database"
        ).build()

    @Provides
    fun providePokeDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()

}