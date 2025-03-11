package com.oscar.themovieapp.data.data_local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokes(pokes: MovieDBEntity)

    @Query("SELECT * FROM movies WHERE idImage = :imageId")
    fun getMovieById(imageId: String): MovieDBEntity?

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieDBEntity>

    @Update
    fun updateMovie(movie: MovieDBEntity)

    @Upsert
    fun insertOrUpdateMovie(movie: MovieDBEntity)
}