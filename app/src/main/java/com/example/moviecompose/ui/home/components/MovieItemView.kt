package com.example.moviecompose.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.moviecompose.data.model.Movie
import java.time.LocalDate


@Composable
fun MovieCover(movie: Movie, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier, contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            model = movie.poster,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 32.dp)
        ) {

            Text(
                text = movie.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,

                modifier = Modifier
                    .drawBehind {
                        drawRoundRect(
                            Color.Gray,
                            cornerRadius = CornerRadius(10.dp.toPx())
                        )
                    }
                    .padding(4.dp)

            )
        }
        Column(
            modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(end = 16.dp, top = 32.dp)) {
            Text(
                text = movie.voteAverage.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .drawBehind {
                        drawRoundRect(
                            Color.Red,
                            cornerRadius = CornerRadius(10.dp.toPx())
                        )
                    }
            )
        }

    }
}


@Composable
@Preview(showBackground = true)
fun MoviePresentationPreview() {
    val movie = Movie(
        id = 615656,
        title = "Meg 2: The Trench Meg 2: The Trench Meg 2: The Trench Meg 2: The Trench",
        overview = "An exploratory dive into the deepest depths of the ocean of a daring research team spirals into chaos when a malevolent mining operation threatens their mission and forces them into a high-stakes battle for survival.",
        releaseDate = LocalDate.parse("2023-08-02").toString(),
        voteAverage = 6.9,
        poster = "https://image.tmdb.org/t/p/w200/4m1Au3YkjqsxF8iwQy0fPYSxE0h.jpg",
        savedTimeStamp = 10
    )
    Surface(color = MaterialTheme.colorScheme.surface) {
        MovieCover(
            movie = movie, modifier = Modifier
                .fillMaxSize()
                .shadow(8.dp)
        )
    }
}