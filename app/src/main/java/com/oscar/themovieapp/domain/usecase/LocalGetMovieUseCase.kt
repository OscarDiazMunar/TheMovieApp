package com.oscar.themovieapp.domain.usecase

import com.oscar.themovieapp.commons.BaseUseCase
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import com.oscar.themovieapp.domain.repository.MovieLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalGetMovieUseCase @Inject constructor(
    private val movieLocalRepository: MovieLocalRepository
): BaseUseCase<String, MovieDetailEntity> {

    override fun process(request: String): Flow<MovieDetailEntity> {
        return movieLocalRepository.getDetailMovie(request)
    }
}