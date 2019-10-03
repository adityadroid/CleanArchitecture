package com.mobile.clean.arch.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.clean.arch.data.entities.MoviesData
import io.reactivex.Flowable

@Dao
interface MoviesDao {

    @Query("Select * from movies")
    fun getMovies(): Flowable<List<MoviesData>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movies: List<MoviesData>)

    @Query("DELETE FROM movies")
    fun clear()
}