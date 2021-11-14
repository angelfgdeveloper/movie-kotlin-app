package com.angelfgdeveloper.moviekotlinapp.repository

import com.angelfgdeveloper.moviekotlinapp.data.model.MovieList
import com.angelfgdeveloper.moviekotlinapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDataSource): MovieRepository {

    // Dos maneras de retornar
    override suspend fun getUpcomingMovies(): MovieList {
        return dataSourceRemote.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): MovieList = dataSourceRemote.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSourceRemote.getPopularMovies()

}