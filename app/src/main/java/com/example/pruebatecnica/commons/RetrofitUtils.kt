package com.example.pruebatecnica.commons

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun <T> getRetrofitBuilder(
    endPoint: Class<T>,
    httpClient: OkHttpClient.Builder,
    baseUrl: String = "https://api.themoviedb.org/3/movie/"
): T =
    Retrofit.Builder().baseUrl(baseUrl)
        .client(httpClient.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(endPoint)

fun getApiInterceptor(): Interceptor =
    Interceptor { chain ->
        val url = chain.request().url().newBuilder()
            .addQueryParameter("format", "JSON")
            .build()

        val newRequest =
            chain.request()
                .newBuilder()
                .url(url)
                .addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMDgyMzkzNDQzODA3NWQ2M2YxZGJkYTQwMjNlNzZmYyIsInN1YiI6IjY1MDBmNzJkNTU0NWNhMDExYmE2N2RkYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.4QxbpZq9Tj3uzhA8uv2qLNcCA7NIcGBHDzoC4bWv9t8")
                .build()

        chain.proceed(newRequest)
    }

fun getApiClient(authInterceptor: Interceptor): OkHttpClient.Builder =
    OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)