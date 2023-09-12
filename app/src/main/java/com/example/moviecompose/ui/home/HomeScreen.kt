package com.example.moviecompose.ui.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
                Column() {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 150.dp),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp, start = 16.dp, end = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        state = listState,
                        content = {
                            items(movies){
                                MovieCover(movie = it.toMovie(),
                                    Modifier
                                        .shadow(8.dp))
                            }
                        }
                    )

                    if (listState.isScrolledToTheEnd()){
                        LaunchedEffect(key1 = null, block = {
                            viewModel.getMovies(this)
                        })
                    }


                }

            }
            is MovieUiState.Error -> {
                    Toast.makeText(context,"Api Error",Toast.LENGTH_LONG).show()
            }
        }
    }




}



fun LazyGridState.isScrolledToTheEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
