package com.example.moviecompose.data.model

import com.squareup.moshi.Json

data class MovieResponse(
    val overview: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    val video: Boolean,
    val title: String,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "release_date") val releaseDate: String,
    val popularity: Double,
    @Json(name = "vote_average") val voteAverage: Double,
    val id: Int,
    val adult: Boolean,
    @Json(name = "vote_count") val voteCount: Int
) {
    fun toMovie(): Movie = Movie(
        id = id,
        title = title,
        overview = overview,
        savedTimeStamp = System.currentTimeMillis(),
        releaseDate = "",
        voteAverage = voteAverage,
        poster = BASE_URL_IMG + posterPath,
    )
}

const val BASE_URL_IMG = "https://image.tmdb.org/t/p/w200"
