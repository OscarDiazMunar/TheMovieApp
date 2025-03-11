package com.oscar.themovieapp

import com.oscar.themovieapp.data.data_repository.data_source.local.LocalMovieDataSource
import com.oscar.themovieapp.data.data_repository.data_source.remote.RemoteListMoviesDataSource
import com.oscar.themovieapp.data.data_repository.repository.GetListMoviesRepositoryImpl
import com.oscar.themovieapp.domain.entity.detail.MovieDetailEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetListMoviesRepositoryImplTest {
    private lateinit var remoteListMoviesDataSource: RemoteListMoviesDataSource
    private lateinit var localMovieDataSource: LocalMovieDataSource
    private lateinit var getListMoviesRepository: GetListMoviesRepositoryImpl

    @Before
    fun setUp() {
        remoteListMoviesDataSource = mockk()
        localMovieDataSource = mockk(relaxed = true)
        getListMoviesRepository = GetListMoviesRepositoryImpl(remoteListMoviesDataSource, localMovieDataSource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsMovieDetailEntityAndSavesToLocal() = runBlocking {
        val movieDetail = MovieDetailEntity(id = "1", title = "Test Movie")
        coEvery { remoteListMoviesDataSource.getDetailMovie("1") } returns flowOf(movieDetail)

        val result = getListMoviesRepository.getDetailMovie("1").toList()

        assertEquals(1, result.size)
        assertEquals(movieDetail, result[0])
        coVerify { localMovieDataSource.addMovie(movieDetail) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsEmptyFlowWhenNoMovieDetailFound() = runBlocking {
        coEvery { remoteListMoviesDataSource.getDetailMovie("1") } returns flowOf()

        val result = getListMoviesRepository.getDetailMovie("1").toList()

        assertEquals(emptyList<MovieDetailEntity>(), result)
    }
}