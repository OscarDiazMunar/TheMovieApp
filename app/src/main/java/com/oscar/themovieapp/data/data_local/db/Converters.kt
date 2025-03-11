package com.oscar.themovieapp.data.data_local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oscar.themovieapp.domain.entity.detail.GenreEntity

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromGenreEntityList(value: List<GenreEntity>): String = gson.toJson(value)

    @TypeConverter
    fun toGenreEntityList(value: String): List<GenreEntity> {
        val type = object : TypeToken<List<GenreEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}