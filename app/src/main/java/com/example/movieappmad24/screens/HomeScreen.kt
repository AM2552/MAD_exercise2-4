package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.components.MovieList
import com.example.movieappmad24.components.SimpleTopAppBar
import com.example.movieappmad24.components.SimpleBottomAppBar

@Composable
fun HomeScreen(navController: NavController) {
    MovieAppMAD24Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {SimpleTopAppBar("Movie App", navController, navigationButton = false)},
                bottomBar = {SimpleBottomAppBar(navController)}
            ) { innerPadding ->
                MovieList(movies = getMovies(), padding = Modifier.padding(innerPadding), navController)
            }
        }
    }
}
