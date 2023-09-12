package com.example.moviecompose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviecompose.ui.home.components.HomeGrid
import com.example.moviecompose.ui.home.components.MovieCover

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val context = LocalContext.current
    val listState = rememberLazyGridState()
    val uiState by viewModel.uiState.collectAsState()


    LaunchedEffect(key1 = null, block = {
        viewModel.getMovies(this)
    })

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier
            .align(Alignment.CenterHorizontally)) {
            Text(text = "OXY MOVIES JUA")
        }

        when(uiState) {
            is MovieUiState.Idle -> {
                Column(modifier = Modifier
                    .align(Alignment.CenterHorizontally)) {
                    CircularProgressIndicator(modifier = Modifier.size(100.dp))
                }
            }
            is MovieUiState.Success -> {
                val movies = (uiState as MovieUiState.Success).movies
                Column {
                    HomeGrid(listState = listState, movies = movies )
                    if (listState.isScrolledToTheEnd(15)){
                        LaunchedEffect(key1 = null, block = {
                            viewModel.getMovies(this)
                        })
                    }
                }

            }
            is MovieUiState.Reload -> {
                LaunchedEffect(key1 = null, block = {
                    viewModel.getMovies(this, _page = 4)
                })
            }
            is MovieUiState.Error -> {
                val movies = (uiState as MovieUiState.Error).lastMovies
                Column {
                    HomeGrid(listState = listState, movies, true, onReloadTapped = {
                        viewModel.reload()
                    })
                }
            }
        }
    }




}


fun LazyGridState.isScrolledToTheEnd(buffer:Int):Boolean {
    val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0)
    return(lastVisibleItemIndex > (layoutInfo.totalItemsCount - buffer)) && (layoutInfo.totalItemsCount > 1)
}
