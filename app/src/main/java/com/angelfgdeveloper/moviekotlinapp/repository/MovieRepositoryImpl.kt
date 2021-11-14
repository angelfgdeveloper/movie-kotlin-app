package com.angelfgdeveloper.moviekotlinapp.repository

import com.angelfgdeveloper.moviekotlinapp.data.local.LocalMovieDataSource
import com.angelfgdeveloper.moviekotlinapp.data.model.MovieList
import com.angelfgdeveloper.moviekotlinapp.data.model.toMovieEntity
import com.angelfgdeveloper.moviekotlinapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    // Dos maneras de retornar
    override suspend fun getUpcomingMovies(): MovieList {
        dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
        }

        return dataSourceLocal.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): MovieList {
        dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
        }

        return dataSourceLocal.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): MovieList {
        dataSourceRemote.getPopularMovies().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
        }

        return dataSourceLocal.getPopularMovies()
    }

}