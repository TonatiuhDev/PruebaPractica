package com.example.pruebatecnica.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebatecnica.R
import com.example.pruebatecnica.databinding.FragmentMoviesBinding
import com.example.pruebatecnica.home.HomeViewModel
import com.example.pruebatecnica.home.data.remote.Results
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), MoviesEvents {
    private lateinit var binding: FragmentMoviesBinding

    private val homeViewModel: HomeViewModel by activityViewModels()

    private fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = initViewBinding(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        binding.logout.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun initObservers() {
        binding.progressBar.visibility = View.VISIBLE
        homeViewModel.getMovies()
        homeViewModel.getMovies.observe(viewLifecycleOwner) { result ->
            result?.getMoviesResponse?.let {
                initMoviesRecycler(it.results)
            }
            result?.error?.let {
                //FLujo de error
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun initMoviesRecycler(movies: ArrayList<Results>) {
        if (movies.isNotEmpty()) {
            binding.progressBar.visibility = View.GONE
            binding.moviesRecycler.adapter = MoviesAdapter(movies, this)
            binding.moviesRecycler.layoutManager = GridLayoutManager(
                requireActivity(), 2, LinearLayoutManager.VERTICAL, false
            )
        }
    }

    override fun moveToMovieDetail(movieDetail: Results) {
        homeViewModel.myMovie.value = movieDetail
        requireActivity().findNavController(R.id.navHomeFragmentApp)
            .navigate(R.id.movieDetailFragment)
    }
}