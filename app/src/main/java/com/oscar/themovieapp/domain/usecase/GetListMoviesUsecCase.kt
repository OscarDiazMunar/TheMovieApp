package com.oscar.themovieapp.domain.usecase

import androidx.paging.PagingData
import com.oscar.themovieapp.commons.BaseUseCase
import com.oscar.themovieapp.domain.entity.MoviesEntity
import com.oscar.themovieapp.domain.repository.GetListMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListMoviesUsecCase @Inject constructor(
    private val getListMoviesRepository: GetListMoviesRepository
): BaseUseCase<Unit, Flow<PagingData<MoviesEntity>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<MoviesEntity>> {
        return getListMoviesRepository.getListMovies()
    }
}