package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WatchlistViewModel(
    private val repository: MovieRepository
) : ViewModel(), MovieViewModel {
    private val _favoriteMovies = MutableStateFlow(listOf<MovieWithImg>())
    val favoriteMovies: StateFlow<List<MovieWithImg>> = _favoriteMovies.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getFavoriteMovies().distinctUntilChanged()
                .collect{ listOfMovies ->
                    _favoriteMovies.value = listOfMovies
                }
        }
    }

    override fun toggleFavorite(movieId: Long) {
        viewModelScope.launch {
            val movie = _favoriteMovies.value.find { it.movie.movieId == movieId }
            movie?.let {
                it.movie.isFavorite = !it.movie.isFavorite
                repository.updateMovie(it.movie)
            }
        }
    }
}