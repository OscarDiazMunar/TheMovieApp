package com.oscar.themovieapp.data.data_repository.data_source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oscar.themovieapp.domain.entity.list.MoviesEntity

class MoviesPagingSource(
    private val remoteListMoviesDataSource: RemoteListMoviesDataSource
): PagingSource<Int, MoviesEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesEntity> {

        return try {

            val currentPage = params.key ?: 1
            var allMovies = emptyList<MoviesEntity>()
            remoteListMoviesDataSource.getListMovies(page = currentPage).collect {
                allMovies = it
            }

            LoadResult.Page(
                data = allMovies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (allMovies.isEmpty()) null else currentPage + 1
            )
        }catch (excption: Exception){
            LoadResult.Error(excption)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesEntity>): Int? {
        return state.anchorPosition
    }
}