package com.example.pruebatecnica.home.domain.use_case

import com.example.pruebatecnica.commons.Resource
import com.example.pruebatecnica.home.data.remote.GetMoviesResponse
import com.example.pruebatecnica.home.data.repository.MoviesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val api: MoviesApi) {
    operator fun invoke():
            Flow<Resource<out GetMoviesResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = api.getMovies()
            if (response.page != 0)
                emit(Resource.Success(response))
            else
                emit(Resource.Error("Error not found"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: ""))
        }
    }
}