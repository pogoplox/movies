package com.example.moviecompose.data.model


data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val poster: String,
    val releaseDate: String,
    val savedTimeStamp: Long,
)