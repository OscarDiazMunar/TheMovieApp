package com.oscar.themovieapp.data.data_local.source

import com.oscar.themovieapp.data.data_local.db.MovieDBEntity
import com.oscar.themovieapp.data.data_local.db.MovieDao
import com.oscar.themovieapp.data.data_repository.data_source.local.LocalMovieDataSource
import com.oscar.themovieapp.domain.entity.detail.GenreEntity
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalMovieDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
): LocalMovieDataSource {

    override fun getMovie(): Flow<MovieDetailEntity> = flow {
        emit(movieDao.getMovieById("1"))
    }.map {
        convert(it)
    }

    private fun convert(movie: MovieDBEntity?): MovieDetailEntity {
        return movie?.let {
            MovieDetailEntity(
                genres = it.genres,
                id = it.id,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title
            )
        } ?: MovieDetailEntity()
    }

    override suspend fun addMovie(movie: MovieDetailEntity) =
        movieDao.insertOrUpdateMovie(
            MovieDBEntity(
                genres = movie.genres?.map {
                                         GenreEntity(
                                             id = it.id,
                                             name = it.name
                                         )
                } ?: emptyList(),
                id = movie.id,
                idImage = "1",
                overview = movie.overview,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate,
                title = movie.title
            )
        )
}