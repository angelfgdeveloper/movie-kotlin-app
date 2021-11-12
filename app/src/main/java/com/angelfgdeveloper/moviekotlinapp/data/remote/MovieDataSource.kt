package com.angelfgdeveloper.moviekotlinapp.data.remote

import com.angelfgdeveloper.moviekotlinapp.data.model.MovieList

class MovieDataSource {

    fun getUpcomingMovies(): MovieList {
        return MovieList()
    }

    fun getTopRatedMovies(): MovieList {
        return MovieList()
    }

    fun getPopularMovies(): MovieList {
        return MovieList()
    }

}