package com.oscar.themovieapp.data.data_repository.injection

import com.oscar.themovieapp.data.data_repository.repository.GetListMoviesRepositoryImpl
import com.oscar.themovieapp.data.data_repository.repository.GetMovieLocalRepositoryImpl
import com.oscar.themovieapp.domain.repository.GetListMoviesRepository
import com.oscar.themovieapp.domain.repository.MovieLocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGetAllPokesRepository(getListMoviesRepositoryImpl: GetListMoviesRepositoryImpl): GetListMoviesRepository

    @Binds
    abstract fun bindMovieLocalRepository(getMovieLocalRepositoryImpl: GetMovieLocalRepositoryImpl): MovieLocalRepository

}