package com.oscar.themovieapp.domain.usecase

import com.oscar.themovieapp.commons.BaseUseCase
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import com.oscar.themovieapp.domain.repository.GetListMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailMovieUseCase @Inject constructor(
    private val getListMoviesRepository: GetListMoviesRepository
): BaseUseCase<String, MovieDetailEntity> {

    override fun process(request: String): Flow<MovieDetailEntity> =
         getListMoviesRepository.getDetailMovie(id = request)
}