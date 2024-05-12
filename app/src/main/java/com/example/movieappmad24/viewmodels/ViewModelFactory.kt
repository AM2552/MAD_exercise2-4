package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.data.MovieRepository

class ViewModelFactory(
    private val repository: MovieRepository,
    private val movieId: Long? = null): ViewModelProvider.Factory {
    override fun<T: ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            modelClass.isAssignableFrom(WatchlistViewModel::class.java) -> WatchlistViewModel(repository) as T
            modelClass.isAssignableFrom(DetailsViewModel::class.java) -> DetailsViewModel(repository, movieId!!) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
