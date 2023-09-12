package com.example.moviecompose.domain.usecase

import com.example.moviecompose.data.Results
import com.example.moviecompose.data.model.MovieResponse
import com.example.moviecompose.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

     suspend fun invoke(pag:Int = 1)  : Flow<Results.Response<List<MovieResponse>>> {
        return moviesRepository.getMovies(pag)
    }
}