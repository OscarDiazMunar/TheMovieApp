package com.oscar.themovieapp

import com.oscar.themovieapp.data.data_local.db.MovieDBEntity
import com.oscar.themovieapp.data.data_local.db.MovieDao
import com.oscar.themovieapp.data.data_local.source.LocalMovieDataSourceImpl
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocalMovieDataSourceImplTest {
    private lateinit var movieDao: MovieDao
    private lateinit var localMovieDataSource: LocalMovieDataSourceImpl

    @Before
    fun setUp() {
        movieDao = mockk()
        localMovieDataSource = LocalMovieDataSourceImpl(movieDao)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsMovieDetailEntity() = runBlocking {
        val movieDBEntity = MovieDBEntity(
            genres = emptyList(),
            id = "1",
            idImage = "1",
            overview = "Overview",
            posterPath = "path",
            releaseDate = "2023-01-01",
            title = "Test Movie"
        )
        coEvery { movieDao.getMovieById("1") } returns movieDBEntity

        val result = localMovieDataSource.getMovie().toList()

        assertEquals(1, result.size)
        assertEquals("Test Movie", result[0].title)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsEmptyMovieDetailEntityWhenNoMovieFound() = runBlocking {
        coEvery { movieDao.getMovieById("1") } returns null

        val result = localMovieDataSource.getMovie().toList()

        assertEquals(1, result.size)
        assertEquals("", result[0].title)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun addsMovieToLocalDatabase() = runBlocking {
        val movieDetailEntity = MovieDetailEntity(
            genres = emptyList(),
            id = "1",
            overview = "Overview",
            posterPath = "path",
            releaseDate = "2023-01-01",
            title = "Test Movie"
        )
        coEvery { movieDao.insertOrUpdateMovie(any()) } returns Unit

        localMovieDataSource.addMovie(movieDetailEntity)

        coVerify { movieDao.insertOrUpdateMovie(any()) }
    }
}