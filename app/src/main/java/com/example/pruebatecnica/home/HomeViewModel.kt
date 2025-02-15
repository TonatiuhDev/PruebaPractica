package com.example.pruebatecnica.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebatecnica.commons.Resource
import com.example.pruebatecnica.home.data.remote.Results
import com.example.pruebatecnica.home.domain.model.GetMoviesState
import com.example.pruebatecnica.home.domain.use_case.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _getMovies: MutableLiveData<GetMoviesState> = MutableLiveData()

    val getMovies: MutableLiveData<GetMoviesState> get() = _getMovies

    val myMovie: MutableLiveData<Results> = MutableLiveData()

    fun getMovies() {
        getMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _getMovies.postValue(GetMoviesState(isLoading = true))
                }
                is Resource.Success -> {
                    _getMovies.postValue(
                        GetMoviesState(isLoading = false, getMoviesResponse = result.data)
                    )
                }
                is Resource.Error -> {
                    _getMovies.postValue(
                        GetMoviesState(isLoading = false, error = result.message)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}