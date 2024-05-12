package com.example.movieappmad24.data

import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImg
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao, private val movieImgDao: MovieImgDao) {

    suspend fun addMovie(movie: Movie) = movieDao.add(movie)

    suspend fun updateMovie(movie: Movie) = movieDao.update(movie)

    suspend fun deleteMovie(movie: Movie) = movieDao.delete(movie)

    fun getAllMovies(): Flow<List<MovieWithImg>> = movieDao.getAll()

    fun getFavoriteMovies(): Flow<List<MovieWithImg>> = movieDao.getFavorites()

    fun getByIdWithImages(id: Long): Flow<MovieWithImg?> = movieDao.getWithImages(id)
}