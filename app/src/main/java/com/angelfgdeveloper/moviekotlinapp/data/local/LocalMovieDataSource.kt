package com.angelfgdeveloper.moviekotlinapp.data.local

import com.angelfgdeveloper.moviekotlinapp.data.model.MovieEntity
import com.angelfgdeveloper.moviekotlinapp.data.model.MovieList
import com.angelfgdeveloper.moviekotlinapp.data.model.toMovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter { movie ->
            movie.movie_type == "upcoming"
        }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter { movie ->
            movie.movie_type == "toprated"
        }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { movie ->
            movie.movie_type == "popular"
        }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity) {
        movieDao.saveMovie(movie)
    }

}