package com.angelfgdeveloper.moviekotlinapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.angelfgdeveloper.moviekotlinapp.core.Resource
import com.angelfgdeveloper.moviekotlinapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo: MovieRepository) : ViewModel() {

    fun getAllViewModels() {
        fun fetchUpcomingMovies() = liveData(Dispatchers.IO) {
            emit(Resource.Loading())

            try {
                emit(Resource.Success(repo.getUpcomingMovies()))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }

        }

        fun fetchTopRatedMovies() = liveData(Dispatchers.IO) {
            emit(Resource.Loading())

            try {
                emit(Resource.Success(repo.getTopRatedMovies()))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }

        }

        fun fetchPopularMovies() = liveData(Dispatchers.IO) {
            emit(Resource.Loading())

            try {
                emit(Resource.Success(repo.getPopularMovies()))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }

        }
    }

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(
                Resource.Success(
                    Triple(
                        repo.getUpcomingMovies(),
                        repo.getTopRatedMovies(),
                        repo.getPopularMovies()
                    )
//                    NTuple4(
//                        repo.getUpcomingMovies(),
//                        repo.getTopRatedMovies(),
//                        repo.getPopularMovies(),
//                        repo.getPopularMovies(),
//                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

    fun fetchMainScreen2Movies() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())

        try {
            emit(
                Resource.Success(
                    Triple(
                        repo.getUpcomingMovies(),
                        repo.getTopRatedMovies(),
                        repo.getPopularMovies()
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

}

class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}

// Ejemplo para llamar simultaneamente n tuplas en el servidor
data class NTuple4<T1, T2, T3, T4>(val t1: T1, val t2: T2, val t3: T3, val t4: T4)