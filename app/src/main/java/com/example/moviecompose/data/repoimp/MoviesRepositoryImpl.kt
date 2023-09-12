package com.example.moviecompose.data.repoimp

import com.example.moviecompose.data.Results
import com.example.moviecompose.data.api.ApiService
import com.example.moviecompose.data.model.MovieResponse
import com.example.moviecompose.domain.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: ApiService
) : MoviesRepository {
    override suspend fun getMovies(page: Int): Flow<Results.Response<List<MovieResponse>>>  = flow {
      //  delay(2000)
        emit(moviesService.getMovies(false, page = page))
    }
        .catch {
           when (it){
               is HttpException -> {
                   throw Results.ApiError(it.message())
               }
           }
        }
        .map {
            Results.Response(it.results)
        }
        .flowOn(Dispatchers.IO)
}



//            emit(moviesService.getMovies(false, page = 1))