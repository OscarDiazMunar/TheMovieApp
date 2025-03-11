package com.oscar.themovieapp.data.data_remote.networking.model.detail

import com.squareup.moshi.Json

data class DetailMovieDTO(
    val genres: List<GenreDTO>,
    val id: String,
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    val title: String,
)