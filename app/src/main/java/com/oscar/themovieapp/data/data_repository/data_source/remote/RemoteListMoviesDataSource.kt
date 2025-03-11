package com.oscar.themovieapp.data.data_repository.data_source.remote

import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import com.oscar.themovieapp.domain.entity.list.MoviesEntity
import kotlinx.coroutines.flow.Flow

interface RemoteListMoviesDataSource {
    fun getListMovies(page: Int): Flow<List<MoviesEntity>>

    fun getDetailMovie(id: String): Flow<MovieDetailEntity>
}