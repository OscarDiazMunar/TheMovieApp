package com.oscar.themovieapp.data.data_repository.data_source.local

import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

interface LocalMovieDataSource {
    fun getMovie(): Flow<MovieDetailEntity>

    suspend fun addMovie(movie: MovieDetailEntity)
}