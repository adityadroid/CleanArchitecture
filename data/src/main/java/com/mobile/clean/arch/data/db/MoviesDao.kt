package com.mobile.clean.arch.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.clean.arch.data.entities.MoviesItemData
import io.reactivex.Flowable

@Dao
interface MoviesDao {

    @Query("Select * from movies")
    fun getMovies(): Flowable<List<MoviesItemData>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movies: List<MoviesItemData>)

    @Query("DELETE FROM movies")
    fun clear()
}