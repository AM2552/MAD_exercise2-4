package com.example.movieappmad24.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieappmad24.components.MovieRow
import com.example.movieappmad24.components.SimpleTopAppBar
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

@Composable
fun DetailScreen(navController: NavController, movieId: String?, movieTitle: String?) {
    val movie = getMovies().find { it.id == movieId }
    MovieAppMAD24Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    if (movieTitle != null) {
                        SimpleTopAppBar(movieTitle, navController, navigationButton = true)
                    }
                },
            ) { innerPadding ->
                if (movie != null) {
                    Column {
                        Box(modifier = Modifier.padding(innerPadding)) {
                            MovieRow(movie = movie)
                        }
                        Box {
                            LazyRow {
                                items(movie.images) { image ->
                                    Card(
                                        elevation = CardDefaults.cardElevation(5.dp),
                                        modifier = Modifier.padding(5.dp)
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(model = image),
                                            contentDescription = "Movie Image",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.size(260.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

