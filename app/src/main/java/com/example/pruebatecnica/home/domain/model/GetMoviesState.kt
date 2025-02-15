package com.example.pruebatecnica.home.domain.model

import com.example.pruebatecnica.home.data.remote.GetMoviesResponse

data class GetMoviesState(
    val isLoading: Boolean = false,
    val getMoviesResponse: GetMoviesResponse? = null,
    val error: String? = null
)