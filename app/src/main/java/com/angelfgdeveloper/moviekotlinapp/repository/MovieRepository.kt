package com.angelfgdeveloper.moviekotlinapp.repository

import com.angelfgdeveloper.moviekotlinapp.data.model.MovieList

interface MovieRepository {
    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}