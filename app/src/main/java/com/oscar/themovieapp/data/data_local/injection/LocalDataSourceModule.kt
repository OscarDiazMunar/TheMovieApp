package com.oscar.themovieapp.data.data_local.injection

import com.oscar.themovieapp.data.data_local.source.LocalMovieDataSourceImpl
import com.oscar.themovieapp.data.data_repository.data_source.local.LocalMovieDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindMovieDataSource(localPokeDataSourceImpl: LocalMovieDataSourceImpl): LocalMovieDataSource
}