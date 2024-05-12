package com.example.movieappmad24.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.movieappmad24.models.MovieImg

@Dao
interface MovieImgDao {
    @Insert
    suspend fun add(image: MovieImg)

    @Insert
    suspend fun addAll(images: List<MovieImg>)

    @Update
    suspend fun update(image: MovieImg)

    @Delete
    suspend fun delete(image: MovieImg)

}