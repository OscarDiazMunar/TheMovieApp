package com.oscar.themovieapp.data.data_remote.injection

import com.oscar.themovieapp.data.data_remote.source.RemoteListMoviesDataSourceImpl
import com.oscar.themovieapp.data.data_repository.data_source.remote.RemoteListMoviesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindRemoteListMoviesDataSource(remoteListMoviesDataSourceImpl: RemoteListMoviesDataSourceImpl): RemoteListMoviesDataSource
}