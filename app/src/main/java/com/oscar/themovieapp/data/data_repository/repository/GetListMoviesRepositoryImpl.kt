package com.oscar.themovieapp.data.data_repository.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.oscar.themovieapp.data.data_repository.data_source.remote.MoviesPagingSource
import com.oscar.themovieapp.data.data_repository.data_source.remote.RemoteListMoviesDataSource
import com.oscar.themovieapp.domain.entity.MoviesEntity
import com.oscar.themovieapp.domain.repository.GetListMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListMoviesRepositoryImpl @Inject constructor(
    private val remoteListMoviesDataSource: RemoteListMoviesDataSource,
): GetListMoviesRepository {
    override fun getListMovies(): Flow<PagingData<MoviesEntity>> {
        return Pager(
            initialKey = 1,
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { MoviesPagingSource(remoteListMoviesDataSource) }
        ).flow
    }
}