package com.oscar.themovieapp.data.data_local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.oscar.themovieapp.domain.entity.detail.GenreEntity

@Entity(tableName = "movies")
@TypeConverters(Converters::class)
data class MovieDBEntity(
    val genres: List<GenreEntity>,
    @PrimaryKey val idImage: String,
    val id: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
)