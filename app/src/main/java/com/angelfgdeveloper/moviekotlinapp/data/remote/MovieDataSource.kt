package com.angelfgdeveloper.moviekotlinapp.data.remote

import com.angelfgdeveloper.moviekotlinapp.application.AppConstants
import com.angelfgdeveloper.moviekotlinapp.data.model.MovieList
import com.angelfgdeveloper.moviekotlinapp.repository.WebService

class MovieDataSource(private val webService: WebService) {

    // Dos maneras de retornar
    suspend fun getUpcomingMovies(): MovieList {
        return webService.getUpcomingMovies(AppConstants.API_KEY)
    }

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)

}