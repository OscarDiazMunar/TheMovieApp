package com.oscar.themovieapp.domain.repository

import androidx.paging.PagingData
import com.oscar.themovieapp.domain.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

interface GetListMoviesRepository {
    fun getListMovies(): Flow<PagingData<MoviesEntity>>
}