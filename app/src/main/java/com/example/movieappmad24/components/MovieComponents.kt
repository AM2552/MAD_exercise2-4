package com.example.movieappmad24.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.Screen

@Composable
fun MovieList(movies: List<Movie> = getMovies(), padding: Modifier, navController: NavController) {
    LazyColumn(modifier = padding) {
        items(movies) { movie ->
            MovieRow(movie) {movieId->
                // use of the sealed class Screen
                navController.navigate(Screen.Detail.passIdAndTitle(movieId, movie.title))
            }
        }
    }
}


@Composable
fun MovieRow(movie: Movie,
             onItemClick: (String) -> Unit = {}
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            onItemClick(movie.id)
        },
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            MovieImage(movie.images)
            MovieDetails(movie)
        }
    }
}

@Composable
fun MovieImage(images: List<String>) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(images[2])
                    .build()
            ),
            contentDescription = "Movie image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.secondary,
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Add to watchlist"
            )
        }
    }
}

@Composable
fun MovieDetails(movie: Movie) {
    var expanded by remember { mutableStateOf(false) }
    val arrowRotationAngle by animateFloatAsState(targetValue = if (expanded) 180f else 0f)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = movie.title, style = MaterialTheme.typography.titleLarge)
        Icon(modifier = Modifier
            .clickable { expanded = !expanded }
            .rotate(arrowRotationAngle),
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "Show details")
    }
    AnimatedVisibility(visible = expanded) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(text = "Director: ${movie.director}")
            Text(text = "Released: ${movie.year}")
            Text(text = "Genre: ${movie.genre}")
            Text(text = "Actors: ${movie.actors}")
            Text(text = "Rating: ${movie.rating}")
            Spacer(Modifier.height(12.dp))
            Divider(thickness = 2.dp)
            Spacer(Modifier.height(12.dp))
            Text(text = "Plot: ${movie.plot}")
        }
    }
}