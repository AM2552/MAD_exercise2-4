package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        // Home Screen
        composable(
            route= Screen.Home.route
        ) {
            HomeScreen(navController)
        }

        // Detail Screen
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_KEY) {type = NavType.StringType },
                navArgument(DETAIL_ARGUMENT_KEY2) {type = NavType.StringType})
        ) { backStackEntry ->
            DetailScreen(
                navController,
                movieId = backStackEntry.arguments?.getString("movieId"),
                movieTitle = backStackEntry.arguments?.getString("movieTitle"))
        }

        // Watchlist Screen
        composable(
            route = Screen.Watchlist.route
        ) {
            WatchlistScreen(navController)
        }
    }
}