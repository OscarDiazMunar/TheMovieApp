package com.oscar.themovieapp

import com.oscar.themovieapp.data.data_repository.data_source.local.LocalMovieDataSource
import com.oscar.themovieapp.data.data_repository.repository.GetMovieLocalRepositoryImpl
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import com.oscar.themovieapp.domain.repository.MovieLocalRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMovieLocalRepositoryImplTest {

    private lateinit var localMovieDataSource: LocalMovieDataSource
    private lateinit var movieLocalRepository: MovieLocalRepository

    @Before
    fun setUp() {
        localMovieDataSource = mockk()
        movieLocalRepository = GetMovieLocalRepositoryImpl(localMovieDataSource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsMovieDetailEntity() = runBlocking {
        val movieDetail = MovieDetailEntity(id = "1", title = "Test Movie")
        coEvery { localMovieDataSource.getMovie() } returns flowOf(movieDetail)

        val result = movieLocalRepository.getDetailMovie("1").single()

        assertEquals(movieDetail, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsEmptyFlowWhenNoMovieFound() = runBlocking {
        coEvery { localMovieDataSource.getMovie() } returns flowOf()

        val result = movieLocalRepository.getDetailMovie("1").toList()

        assertEquals(emptyList<MovieDetailEntity>(), result)
    }
}