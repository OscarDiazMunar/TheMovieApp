package com.oscar.themovieapp.data.data_remote.networking

import com.oscar.themovieapp.commons.Constants
import com.oscar.themovieapp.data.data_remote.networking.model.AllMoviesDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GetMoviesService {

    @Headers("Authorization: ${Constants.token}")
    @GET("discover/movie")
    suspend fun getListMovies(@Query("include_adult") includeAdult: Boolean = false ,
                              @Query("include_video") includeVideo: Boolean =false,
                              @Query("language") language: String ="en-US",
                              @Query("page") page: Int,
                              @Query("sort_by") sortBy: String = "popularity.desc"): AllMoviesDTO
}