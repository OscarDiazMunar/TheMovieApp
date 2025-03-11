package com.oscar.themovieapp.domain.repository

import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalRepository {
    fun getDetailMovie(id: String): Flow<MovieDetailEntity>
}