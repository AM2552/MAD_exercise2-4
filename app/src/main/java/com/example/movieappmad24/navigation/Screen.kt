package com.example.movieappmad24.navigation

const val DETAIL_ARGUMENT_KEY = "movieId"
sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home")
    data object DetailScreen : Screen("detail/{$DETAIL_ARGUMENT_KEY}") {
        fun withId(id: Long): String {
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = id.toString())
        }
    }
    data object WatchlistScreen : Screen("watchlist")
}