package com.example.moviecompose.domain.repositories

import com.example.moviecompose.data.Results
import com.example.moviecompose.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow


interface MoviesRepository {

    suspend fun getMovies(page: Int) : Flow<Results.Response<List<MovieResponse>>>



}