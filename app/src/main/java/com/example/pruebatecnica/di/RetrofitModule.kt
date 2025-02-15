package com.example.pruebatecnica.di

import com.example.pruebatecnica.commons.getApiClient
import com.example.pruebatecnica.commons.getApiInterceptor
import com.example.pruebatecnica.commons.getRetrofitBuilder
import com.example.pruebatecnica.home.data.repository.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi =
        getRetrofitBuilder(MoviesApi::class.java, getApiClient(getApiInterceptor()))

}