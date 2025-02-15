package com.example.pruebatecnica.home.data.repository

import com.example.pruebatecnica.home.data.remote.GetMoviesResponse
import retrofit2.http.GET

interface MoviesApi {
    @GET("now_playing")
    suspend fun getMovies(): GetMoviesResponse
}