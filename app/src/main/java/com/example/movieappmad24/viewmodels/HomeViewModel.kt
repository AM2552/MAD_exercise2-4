package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.models.MovieWithImg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel(), MovieViewModel {
    private val _movies = MutableStateFlow(listOf<MovieWithImg>())
    val movies: StateFlow<List<MovieWithImg>> = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMovies().distinctUntilChanged()
                .collect{ listOfMovies ->
                    _movies.value = listOfMovies
                }
        }
    }

    override fun toggleFavorite(movieId: Long) {
        viewModelScope.launch {
            val movie = _movies.value.find { it.movie.movieId == movieId }
            movie?.let {
                it.movie.isFavorite = !it.movie.isFavorite
                repository.updateMovie(it.movie)
            }
        }
    }
}