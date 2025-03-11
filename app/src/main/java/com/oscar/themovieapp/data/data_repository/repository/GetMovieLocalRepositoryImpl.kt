package com.oscar.themovieapp.data.data_repository.repository

import com.oscar.themovieapp.data.data_repository.data_source.local.LocalMovieDataSource
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import com.oscar.themovieapp.domain.repository.MovieLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieLocalRepositoryImpl @Inject constructor(
    private val localMovieDataSource: LocalMovieDataSource
): MovieLocalRepository {

    override fun getDetailMovie(id: String): Flow<MovieDetailEntity> = localMovieDataSource.getMovie()
}