package com.angelfgdeveloper.moviekotlinapp.data.local

import com.angelfgdeveloper.moviekotlinapp.data.model.MovieEntity
import com.angelfgdeveloper.moviekotlinapp.data.model.MovieList
import com.angelfgdeveloper.moviekotlinapp.data.model.toMovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList {
        val movieList: MovieList
        withContext(Dispatchers.IO) {
            movieList = movieDao.getAllMovies().filter { movie ->
                movie.movie_type == "upcoming"
            }.toMovieList()
        }
        return movieList
    }

    suspend fun getTopRatedMovies(): MovieList {
        val movieList: MovieList
        withContext(Dispatchers.IO) {
            movieList = movieDao.getAllMovies().filter { movie ->
                movie.movie_type == "toprated"
            }.toMovieList()
        }
        return movieList
    }

    suspend fun getPopularMovies(): MovieList {
        val movieList: MovieList
        withContext(Dispatchers.IO) {
            movieList = movieDao.getAllMovies().filter { movie ->
                movie.movie_type == "popular"
            }.toMovieList()
        }
        return movieList
    }

    suspend fun saveMovie(movie: MovieEntity) {
        withContext(Dispatchers.IO) {
            movieDao.saveMovie(movie)
        }
    }

}