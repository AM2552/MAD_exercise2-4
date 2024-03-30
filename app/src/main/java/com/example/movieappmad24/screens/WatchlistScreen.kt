package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.components.MovieList
import com.example.movieappmad24.components.SimpleBottomAppBar
import com.example.movieappmad24.components.SimpleTopAppBar
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

@Composable
fun WatchlistScreen(navController: NavController) {
    // chosen movies to be displayed in the watchlist
    val movies = getMovies().filter { it.title == "Avatar" || it.title == "The Avengers" || it.title == "Interstellar"}

    MovieAppMAD24Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { SimpleTopAppBar("Your Watchlist", navController, navigationButton = false) },
                bottomBar = { SimpleBottomAppBar(navController)}
            ) {innerPadding ->
                MovieList(movies = movies, padding = Modifier.padding(innerPadding), navController = navController)
            }
        }
    }
}