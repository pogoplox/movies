package com.example.moviecompose.data.api

import com.example.moviecompose.data.model.PagResponse
import com.example.moviecompose.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): PagResponse<MovieResponse>


}