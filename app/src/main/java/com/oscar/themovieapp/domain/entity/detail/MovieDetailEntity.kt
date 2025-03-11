package com.oscar.themovieapp.domain.entity.detail

data class MovieDetailEntity(
    val genres: List<GenreEntity>? = null,
    val id: String = "0",
    val overview: String = "",
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String ="",
)