package com.oscar.themovieapp.data.data_remote.networking.model

import com.squareup.moshi.Json

data class ResultDTO(
    @Json(name = "genre_ids")
    val genreIds: List<Long>,
    val id: String,
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "release_date")
    val releaseDate: String,
    val title: String,
)