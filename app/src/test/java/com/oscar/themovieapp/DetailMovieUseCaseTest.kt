package com.oscar.themovieapp

import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import com.oscar.themovieapp.domain.repository.GetListMoviesRepository
import com.oscar.themovieapp.domain.usecase.DetailMovieUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailMovieUseCaseTest {

    private lateinit var getListMoviesRepository: GetListMoviesRepository
    private lateinit var detailMovieUseCase: DetailMovieUseCase

    @Before
    fun setUp() {
        getListMoviesRepository = mockk()
        detailMovieUseCase = DetailMovieUseCase(getListMoviesRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsMovieDetailEntity() = runBlocking {
        val movieDetail = MovieDetailEntity(id = "1", title = "Test Movie")
        coEvery { getListMoviesRepository.getDetailMovie("1") } returns flowOf(movieDetail)

        val result = detailMovieUseCase.process("1").toList()

        assertEquals(1, result.size)
        assertEquals(movieDetail, result[0])
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsEmptyFlowWhenNoMovieDetailFound() = runBlocking {
        coEvery { getListMoviesRepository.getDetailMovie("1") } returns flowOf()

        val result = detailMovieUseCase.process("1").toList()

        assertEquals(emptyList<MovieDetailEntity>(), result)
    }
}