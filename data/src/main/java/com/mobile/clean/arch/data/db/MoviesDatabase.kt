package com.mobile.clean.arch.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mobile.clean.arch.data.entities.MoviesItemData

@Database(entities = [MoviesItemData::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}