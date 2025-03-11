package com.oscar.themovieapp.data.data_remote.networking

import com.oscar.themovieapp.data.data_remote.networking.model.detail.DetailMovieDTO
import com.oscar.themovieapp.data.data_remote.networking.model.list.AllMoviesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMoviesService {

    @GET("discover/movie")
    suspend fun getListMovies(@Query("include_adult") includeAdult: Boolean = false ,
                              @Query("include_video") includeVideo: Boolean =false,
                              @Query("language") language: String ="en-US",
                              @Query("page") page: Int,
                              @Query("sort_by") sortBy: String = "popularity.desc"): AllMoviesDTO

    @GET("movie/{id}")
    suspend fun getDetailMovie(@Path("id") id: String): DetailMovieDTO
}