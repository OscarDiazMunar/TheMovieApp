package com.oscar.themovieapp.domain.usecase

import androidx.paging.PagingData
import com.oscar.themovieapp.commons.BaseUseCase
import com.oscar.themovieapp.domain.entity.list.MoviesEntity
import com.oscar.themovieapp.domain.repository.GetListMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListMoviesUsecCase @Inject constructor(
    private val getListMoviesRepository: GetListMoviesRepository
): BaseUseCase<Unit, PagingData<MoviesEntity>> {

    override fun process(request: Unit): Flow<PagingData<MoviesEntity>> =
        getListMoviesRepository.getListMovies()

}