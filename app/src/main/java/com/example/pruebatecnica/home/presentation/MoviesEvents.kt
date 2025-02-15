package com.example.pruebatecnica.home.presentation

import com.example.pruebatecnica.home.data.remote.Results

interface MoviesEvents {
    fun moveToMovieDetail(movieDetail: Results)
}