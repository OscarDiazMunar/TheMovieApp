package com.oscar.themovieapp.data.data_remote.source

import com.oscar.themovieapp.data.data_remote.networking.GetMoviesService
import com.oscar.themovieapp.data.data_remote.networking.model.detail.DetailMovieDTO
import com.oscar.themovieapp.data.data_remote.networking.model.list.ResultDTO
import com.oscar.themovieapp.data.data_repository.data_source.remote.RemoteListMoviesDataSource
import com.oscar.themovieapp.domain.entity.detail.GenreEntity
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import com.oscar.themovieapp.domain.entity.list.MoviesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteListMoviesDataSourceImpl @Inject constructor(
    private val getMoviesService: GetMoviesService
): RemoteListMoviesDataSource {
    override fun getListMovies(page: Int): Flow<List<MoviesEntity>> = flow {
        emit(getMoviesService.getListMovies(page = page))
    }.map { movies ->
        convertToEntity(movies.results)
    }

    override fun getDetailMovie(id: String): Flow<MovieDetailEntity> = flow {
        emit(getMoviesService.getDetailMovie(id))
    }.map { movie ->
        convertToEntityDetail(movie)
    }

    private fun convertToEntityDetail(movie: DetailMovieDTO): MovieDetailEntity {
        return MovieDetailEntity(
            genres = movie.genres.map {
                                     GenreEntity(
                                         id = it.id,
                                         name = it.name
                                     )
            },
            id = movie.id,
            overview = movie.overview,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            title = movie.title
        )
    }

    private fun convertToEntity(results: List<ResultDTO>): List<MoviesEntity> {
        return results.map {item ->
            MoviesEntity(
                id = item.id,
                posterPath = item.posterPath,
                releaseDate = item.releaseDate,
                title = item.title
            )
        }
    }
}