package com.oscar.themovieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import com.oscar.themovieapp.domain.usecase.DetailMovieUseCase
import com.oscar.themovieapp.domain.usecase.LocalGetMovieUseCase
import com.oscar.themovieapp.presentation.detail.DetailMovieViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailMovieViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var detailMovieUseCase: DetailMovieUseCase
    private lateinit var localGetMovieUseCase: LocalGetMovieUseCase
    private lateinit var viewModel: DetailMovieViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        detailMovieUseCase = mockk()
        localGetMovieUseCase = mockk()
        viewModel = DetailMovieViewModel(detailMovieUseCase, localGetMovieUseCase)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchesDetailMovieSuccessfully() = runBlocking {
        val movieDetail = MovieDetailEntity(id = "1", title = "Test Movie")
        coEvery { detailMovieUseCase.execute("1") } returns flowOf(movieDetail)

        viewModel.fetchDetailMovie("1")

        assertEquals(movieDetail, viewModel.getDetailFlow.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchesLocalDetailMovieSuccessfully() = runBlocking {
        val movieDetail = MovieDetailEntity(id = "local", title = "Local Test Movie")
        coEvery { localGetMovieUseCase.execute("local") } returns flowOf(movieDetail)

        viewModel.fetchDetailMovie("local")

        assertEquals(movieDetail, viewModel.getDetailFlow.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchesEmptyDetailMovieWhenNotFound() = runBlocking {
        coEvery { detailMovieUseCase.execute("1") } returns flowOf()

        viewModel.fetchDetailMovie("1")

        assertEquals(MovieDetailEntity(), viewModel.getDetailFlow.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun fetchesEmptyLocalDetailMovieWhenNotFound() = runBlocking {
        coEvery { localGetMovieUseCase.execute("local") } returns flowOf()

        viewModel.fetchDetailMovie("local")

        assertEquals(MovieDetailEntity(), viewModel.getDetailFlow.value)
    }
}