package com.example.pruebatecnica.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pruebatecnica.commons.setImage
import com.example.pruebatecnica.databinding.FragmentMovieDetailBinding
import com.example.pruebatecnica.home.HomeViewModel
import com.example.pruebatecnica.home.data.remote.Results
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding

    private val homeViewModel: HomeViewModel by activityViewModels()

    private fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = initViewBinding(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        val myMovie = homeViewModel.myMovie.value ?: Results()
        binding.apply {
            movieImage.setImage("https://image.tmdb.org/t/p/w500" + myMovie.backdropPath)
            movieName.text = myMovie.title
            moviePremierInfo.text = myMovie.releaseDate
            movieClassification.text = if (myMovie.adult) "Solo adultos" else "Adultos y niños"
            movieDescription.text = myMovie.overview
        }
    }
}