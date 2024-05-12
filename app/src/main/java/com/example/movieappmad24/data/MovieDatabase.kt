package com.example.movieappmad24.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImg
import com.example.movieappmad24.models.getMovies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Movie::class, MovieImg::class],
    version = 15,                // schema version; whenever you change schema you have to increase the version number
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieImgDao(): MovieImgDao


    companion object{
        @Volatile
        private var instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase{
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        instance = it
                        CoroutineScope(Dispatchers.IO).launch {
                            seedDatabase(it)
                        }
                    }
            }
        }

        private fun seedDatabase(database: MovieDatabase) {
            CoroutineScope(Dispatchers.IO).launch {
                val seed = getMovies()
                seed.forEach {movieWithImages ->
                    val movie = movieWithImages.movie
                    val images = movieWithImages.movieImages
                    val movieId = database.movieDao().add(movie)
                    images.forEach { image ->
                        image.movieId = movieId
                        database.movieImgDao().add(image)
                    }
                }
            }
        }
    }
}