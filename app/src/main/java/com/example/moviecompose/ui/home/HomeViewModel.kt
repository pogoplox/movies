package com.example.moviecompose.ui.home

import androidx.lifecycle.ViewModel
import com.example.moviecompose.data.model.MovieResponse
import com.example.moviecompose.domain.repositories.MoviesRepository
import com.example.moviecompose.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _uiState:MutableStateFlow<MovieUiState> = MutableStateFlow(MovieUiState.Idle)
    val uiState: StateFlow<MovieUiState> get() = _uiState


    fun getMovies(scope:CoroutineScope) {
         scope.launch {
             getMoviesUseCase.invoke()
                 .catch {
                     MovieUiState.Error(it)
                 }
                 .collect { apiResponse ->
                     _uiState.update {
                         MovieUiState.Success(apiResponse.response)
                     }
                 }
         }

    }
}


sealed class MovieUiState {
    data class Success(val movies: List<MovieResponse>): MovieUiState()
    object Idle: MovieUiState()
    data class Error(val exception: Throwable): MovieUiState()
}
