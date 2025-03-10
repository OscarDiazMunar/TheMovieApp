package com.oscar.themovieapp.data.data_remote.networking.model

import com.squareup.moshi.Json

data class AllMoviesDTO(
    val page: Long,
    val results: List<ResultDTO>,
    @Json(name = "total_pages")
    val totalPages: Long,
    @Json(name = "total_results")
    val totalResults: Long,
)