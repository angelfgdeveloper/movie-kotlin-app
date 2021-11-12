package com.angelfgdeveloper.moviekotlinapp.repository

import com.angelfgdeveloper.moviekotlinapp.data.model.MovieList
import com.angelfgdeveloper.moviekotlinapp.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource): MovieRepository {

    // Dos maneras de retornar
    override suspend fun getUpcomingMovies(): MovieList {
        return dataSource.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()

}