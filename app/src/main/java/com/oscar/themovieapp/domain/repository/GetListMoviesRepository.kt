package com.oscar.themovieapp.domain.repository

import androidx.paging.PagingData
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import com.oscar.themovieapp.domain.entity.list.MoviesEntity
import kotlinx.coroutines.flow.Flow

interface GetListMoviesRepository {
    fun getListMovies(): Flow<PagingData<MoviesEntity>>

    fun getDetailMovie(id: String): Flow<MovieDetailEntity>
}