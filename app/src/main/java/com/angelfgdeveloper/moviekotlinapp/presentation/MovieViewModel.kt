package com.angelfgdeveloper.moviekotlinapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.angelfgdeveloper.moviekotlinapp.core.Resource
import com.angelfgdeveloper.moviekotlinapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo: MovieRepository): ViewModel() {

    fun fetchUpcomingMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getUpcomingMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

}

class MovieViewModelFactory(private val repo: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}