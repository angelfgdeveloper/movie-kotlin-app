package com.angelfgdeveloper.moviekotlinapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.angelfgdeveloper.moviekotlinapp.R
import com.angelfgdeveloper.moviekotlinapp.core.Resource
import com.angelfgdeveloper.moviekotlinapp.data.remote.MovieDataSource
import com.angelfgdeveloper.moviekotlinapp.databinding.FragmentMovieBinding
import com.angelfgdeveloper.moviekotlinapp.presentation.MovieViewModel
import com.angelfgdeveloper.moviekotlinapp.presentation.MovieViewModelFactory
import com.angelfgdeveloper.moviekotlinapp.repository.MovieRepositoryImpl
import com.angelfgdeveloper.moviekotlinapp.repository.RetrofitClient

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding

    // Inyeccion de dependecias manual
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                MovieDataSource(RetrofitClient.webservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        // allViewModels()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading..")
                }
                is Resource.Success -> {
                    Log.d(
                        "LiveData", "" +
                                "Upcoming: ${result.data.first} \n \n " +
                                "TopRated: ${result.data.second} \n \n " +
                                "Popular: ${result.data.third}")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }
        })

    }

    private fun allViewModels() {
        // No recomendado
//        viewModel.fetchUpcomingMovies().observe(viewLifecycleOwner, Observer { result ->
//            when(result) {
//                is Resource.Loading -> {
//                    Log.d("LiveDataUpcoming", "Loading..")
//                }
//                is Resource.Success -> {
//                    Log.d("LiveDataUpcoming", "${ result.data }")
//                }
//                is Resource.Failure -> {
//                    Log.d("ErrorUpcoming", "${ result.exception }")
//                }
//            }
//        })
//
//        viewModel.fetchTopRatedMovies().observe(viewLifecycleOwner, Observer { result ->
//            when(result) {
//                is Resource.Loading -> {
//                    Log.d("LiveDataTopRated", "Loading..")
//                }
//                is Resource.Success -> {
//                    Log.d("LiveDataTopRated", "${ result.data }")
//                }
//                is Resource.Failure -> {
//                    Log.d("ErrorTopRated", "${ result.exception }")
//                }
//            }
//        })
//
//        viewModel.fetchPopularMovies().observe(viewLifecycleOwner, Observer { result ->
//            when(result) {
//                is Resource.Loading -> {
//                    Log.d("LiveDataPopular", "Loading..")
//                }
//                is Resource.Success -> {
//                    Log.d("LiveDataPopular", "${ result.data }")
//                }
//                is Resource.Failure -> {
//                    Log.d("ErrorPopular", "${ result.exception }")
//                }
//            }
//        })

    }

}