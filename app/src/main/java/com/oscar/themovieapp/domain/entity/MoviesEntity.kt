package com.oscar.themovieapp.domain.entity

data class MoviesEntity(
    val id: Long,
    val originalTitle: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    )