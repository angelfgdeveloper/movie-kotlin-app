package com.angelfgdeveloper.moviekotlinapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.angelfgdeveloper.moviekotlinapp.data.model.MovieEntity

@Dao
interface MovieDao {

    // Consultas
    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

}