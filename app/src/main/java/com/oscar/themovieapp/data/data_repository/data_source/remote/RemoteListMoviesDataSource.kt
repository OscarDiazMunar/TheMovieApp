package com.oscar.themovieapp.data.data_repository.data_source.remote

import com.oscar.themovieapp.domain.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

interface RemoteListMoviesDataSource {
    fun getListMovies(page: Int): Flow<List<MoviesEntity>>
}