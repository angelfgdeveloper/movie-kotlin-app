package com.angelfgdeveloper.moviekotlinapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelfgdeveloper.moviekotlinapp.core.BaseConcatHolder
import com.angelfgdeveloper.moviekotlinapp.databinding.TopRatedMoviesRowBinding
import com.angelfgdeveloper.moviekotlinapp.ui.movie.adapters.MovieAdapter

class TopRatedConcatAdapter(
    private val moviesAdapter: MovieAdapter
) : RecyclerView.Adapter<BaseConcatHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding =
            TopRatedMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder) {
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: TopRatedMoviesRowBinding) :
        BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.recyclerViewTopRatedMovies.adapter = adapter
        }
    }

}