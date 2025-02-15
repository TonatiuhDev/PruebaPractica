package com.example.pruebatecnica.home.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.R
import com.example.pruebatecnica.commons.setImage
import com.example.pruebatecnica.databinding.ItemMovieBinding
import com.example.pruebatecnica.home.data.remote.Results

class MoviesAdapter(
    private val movies: List<Results>,
    private val moviesEvents: MoviesEvents
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        with(holder) {
            val myMovie = movies[position]
            binding.apply {
                movieTitle.text = myMovie.title
                movieClassification.text = if (myMovie.adult) "Solo adultos" else "Adultos y niños"
                movieImage.setImage("https://image.tmdb.org/t/p/w500" + myMovie.posterPath)
            }
            holder.itemView.setOnClickListener {
                moviesEvents.moveToMovieDetail(myMovie)
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovieBinding.bind(view)
    }
}