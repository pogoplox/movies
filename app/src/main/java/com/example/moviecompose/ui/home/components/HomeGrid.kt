package com.example.moviecompose.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.moviecompose.data.model.MovieResponse

@Composable
fun HomeGrid(listState:LazyGridState, movies: List<MovieResponse>, hasError:Boolean = false, onReloadTapped: () -> Unit = {}){
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
            if (hasError){
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    HomeError(onReload = {
                        onReloadTapped.invoke()
                    })
                }
            }
        }
    )
}
@Composable
fun HomeError(onReload: () -> Unit){
    Box(contentAlignment = Alignment.BottomCenter) {
        FilledTonalButton(onClick = {
            onReload.invoke()
        }) {
            Text(text = "Ha ocurrido un error re loco",)
        }
    }

}