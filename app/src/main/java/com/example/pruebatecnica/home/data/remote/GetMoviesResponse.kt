package com.example.pruebatecnica.home.data.remote

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    val dates: Dates = Dates(),
    val page: Int = 0,
    val results: ArrayList<Results> = arrayListOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)

data class Dates(
    val maximum: String = "",
    val minimum: String = ""
)

data class Results(
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("genre_ids")
    val genreIds: ArrayList<Int> = arrayListOf(),
    val id: Int = 0,
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0
)