package com.mobile.clean.arch.data.repository

import com.mobile.clean.arch.domain.entities.MoviesEntity
import io.reactivex.Flowable

interface MoviesDataStore {
    fun getMovies(
        mediaType: String,
        timeWindow: String
    ): Flowable<MoviesEntity>
}