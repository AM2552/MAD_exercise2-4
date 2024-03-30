package com.example.movieappmad24.navigation

const val DETAIL_ARGUMENT_KEY = "movieId"
const val DETAIL_ARGUMENT_KEY2 = "movieTitle"

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Detail: Screen("detail_screen/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_KEY2}") {
        fun passIdAndTitle(movieId: String, movieTitle: String): String {
            return "detail_screen/$movieId/$movieTitle"
        }
    }
    object Watchlist: Screen("watchlist_screen")
}