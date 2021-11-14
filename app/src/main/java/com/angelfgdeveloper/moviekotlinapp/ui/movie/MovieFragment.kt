package com.angelfgdeveloper.moviekotlinapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.angelfgdeveloper.moviekotlinapp.R
import com.angelfgdeveloper.moviekotlinapp.core.Resource
import com.angelfgdeveloper.moviekotlinapp.data.local.AppDatabase
import com.angelfgdeveloper.moviekotlinapp.data.local.LocalMovieDataSource
import com.angelfgdeveloper.moviekotlinapp.data.model.Movie
import com.angelfgdeveloper.moviekotlinapp.data.remote.RemoteMovieDataSource
import com.angelfgdeveloper.moviekotlinapp.databinding.FragmentMovieBinding
import com.angelfgdeveloper.moviekotlinapp.presentation.MovieViewModel
import com.angelfgdeveloper.moviekotlinapp.presentation.MovieViewModelFactory
import com.angelfgdeveloper.moviekotlinapp.repository.MovieRepositoryImpl
import com.angelfgdeveloper.moviekotlinapp.repository.RetrofitClient
import com.angelfgdeveloper.moviekotlinapp.ui.movie.adapters.MovieAdapter
import com.angelfgdeveloper.moviekotlinapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.angelfgdeveloper.moviekotlinapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.angelfgdeveloper.moviekotlinapp.ui.movie.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding

    // Inyeccion de dependecias manual
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webservice),
                LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        // allViewModels()

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    // Log.d("LiveData", "Loading..")
                    binding.relativeLayoutProgressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
//                    Log.d("LiveData", "" + "Upcoming: ${result.data.first} \n \n " + "TopRated: ${result.data.second} \n \n " + "Popular: ${result.data.third}")
                    binding.relativeLayoutProgressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }

                    binding.recyclerViewMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.relativeLayoutProgressBar.visibility = View.GONE
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

    override fun onMovieClick(movie: Movie) {
        // Log.d("Movie", "onMovieClick: $movie")
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }

}