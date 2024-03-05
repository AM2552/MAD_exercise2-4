package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        topBar = {TopBar()},
                        bottomBar = {CustomBottomBar()}
                    ) { innerPadding ->
                        MovieList(movies = getMovies(), padding = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie> = getMovies(), padding: Modifier) {
    LazyColumn(modifier = padding) {
        items(movies) { movie ->
            MovieRow(movie)
        }
    }
}


@Composable
fun MovieRow(movie: Movie){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            MovieImage(movie.images)
            MovieDetails(movie)
        }
    }
}

// New composables

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {Text("Movie App")}
    )
}

@Composable
fun CustomBottomBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = contentColorFor(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable(onClick = { /* action TBD */ })
            ) {
                Icon(Icons.Default.Home, contentDescription = "Home")
                Spacer(Modifier.height(7.dp))
                Text("Home", style = MaterialTheme.typography.labelSmall)
            }
            Spacer(Modifier.width(170.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable(onClick = { /* action TBD */ })
            ) {
                Icon(Icons.Default.Star, contentDescription = "Watchlist")
                Spacer(Modifier.height(7.dp))
                Text("Watchlist", style = MaterialTheme.typography.labelSmall)
            }
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
    var expanded by remember {mutableStateOf(false)}
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
