package com.example.moviecompose.ui.home

import androidx.lifecycle.ViewModel
import com.example.moviecompose.data.Results
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

    private val _list: MutableList<MovieResponse> = mutableListOf()

    private var page = 1
    fun getMovies(scope:CoroutineScope, _page:Int = 0) {
         scope.launch {
             getMoviesUseCase.invoke(if (_page> 0) _page else page)
                 .catch { t ->
                     _uiState.update {
                         MovieUiState.Error(t,_list)
                     }
                 }
                 .collect { apiResponse ->
                     _uiState.update {
                         _list.addAll(apiResponse.response)
                         MovieUiState.Success(_list)
                     }
                     page += 1
                 }
         }

    }
    fun reload(){
        _uiState.update {
            MovieUiState.Reload
        }
    }
}




sealed class MovieUiState {
    data class Success(val movies: List<MovieResponse>): MovieUiState()
    object Idle: MovieUiState()
    object Reload: MovieUiState()

    data class Error(val exception: Throwable, val lastMovies:List<MovieResponse>): MovieUiState()
}
