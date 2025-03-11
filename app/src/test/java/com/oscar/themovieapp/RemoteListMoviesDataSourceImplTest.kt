package com.oscar.themovieapp

import com.oscar.themovieapp.data.data_remote.networking.GetMoviesService
import com.oscar.themovieapp.data.data_remote.networking.model.detail.DetailMovieDTO
import com.oscar.themovieapp.data.data_remote.networking.model.list.AllMoviesDTO
import com.oscar.themovieapp.data.data_remote.networking.model.list.ResultDTO
import com.oscar.themovieapp.data.data_remote.source.RemoteListMoviesDataSourceImpl
import com.oscar.themovieapp.domain.entity.list.MoviesEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RemoteListMoviesDataSourceImplTest {
    private lateinit var getMoviesService: GetMoviesService
    private lateinit var remoteListMoviesDataSource: RemoteListMoviesDataSourceImpl

    @Before
    fun setUp() {
        getMoviesService = mockk()
        remoteListMoviesDataSource = RemoteListMoviesDataSourceImpl(getMoviesService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsListOfMoviesEntity() = runBlocking {

        val resultDTO = ResultDTO(id = "1", title = "Test Movie", posterPath = "path", releaseDate = "2023-01-01", genreIds = emptyList(), overview = "")
        val allMoviesDTO = AllMoviesDTO(page = 1, results = listOf(resultDTO), totalPages = 1, totalResults = 1)
        coEvery { getMoviesService.getListMovies(page = 1) } returns allMoviesDTO

        val result = remoteListMoviesDataSource.getListMovies(1).toList()

        assertEquals(1, result.size)
        assertEquals("Test Movie", result[0][0].title)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsMovieDetailEntity() = runBlocking {
        val detailMovieDTO = DetailMovieDTO(
            id = "1", title = "Test Movie", overview = "Overview", posterPath = "path",
            releaseDate = "2023-01-01", genres = listOf()
        )
        coEvery { getMoviesService.getDetailMovie(any()) } returns detailMovieDTO

        val result = remoteListMoviesDataSource.getDetailMovie("1").toList()

        assertEquals(1, result.size)
        assertEquals("Test Movie", result[0].title)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsEmptyListWhenNoMoviesFound() = runBlocking {
        val allMoviesDTO = AllMoviesDTO(page = 1, results = emptyList(), totalPages = 1, totalResults = 1)
        coEvery { getMoviesService.getListMovies(page = 1) } returns allMoviesDTO

        val result = remoteListMoviesDataSource.getListMovies(1).toList()

        assertEquals(1, result.size)
        assertEquals(emptyList<MoviesEntity>(), result[0])
    }

    @ExperimentalCoroutinesApi
    @Test
    fun returnsEmptyFlowWhenNoMovieDetailFound() = runBlocking {
        coEvery { getMoviesService.getDetailMovie(any()) } returns DetailMovieDTO(
            id = "", title = "", overview = "", posterPath = "",
            releaseDate = "", genres = listOf()
        )

        val result = remoteListMoviesDataSource.getDetailMovie("1").toList()

        assertEquals(1, result.size)
        assertEquals("", result[0].title)
    }
}