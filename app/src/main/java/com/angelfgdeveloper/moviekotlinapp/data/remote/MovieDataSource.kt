package com.angelfgdeveloper.moviekotlinapp.data.remote

import com.angelfgdeveloper.moviekotlinapp.application.AppConstants
import com.angelfgdeveloper.moviekotlinapp.data.model.MovieList
import com.angelfgdeveloper.moviekotlinapp.repository.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataSource(private val webService: WebService) {

    // Dos maneras de retornar
    suspend fun getUpcomingMovies(): MovieList {
        val movieList: MovieList
        withContext(Dispatchers.IO) {
            movieList = webService.getUpcomingMovies(AppConstants.API_KEY, AppConstants.API_LANGUAGE)
        }
        return movieList
    }

    suspend fun getTopRatedMovies(): MovieList {
        val movieList: MovieList
        withContext(Dispatchers.IO) {
            movieList = webService.getTopRatedMovies(AppConstants.API_KEY, AppConstants.API_LANGUAGE)
        }
        return movieList
    }

    suspend fun getPopularMovies(): MovieList {
        val movieList: MovieList
        withContext(Dispatchers.IO) {
            movieList = webService.getPopularMovies(AppConstants.API_KEY, AppConstants.API_LANGUAGE)
        }
        return movieList
    }

//    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY, AppConstants.API_LANGUAGE)

}