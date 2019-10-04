package com.mobile.clean.arch.domain.repositories

import com.mobile.clean.arch.domain.entities.MoviesEntity
import io.reactivex.Flowable

interface MoviesRepository {

    fun getMovies(
        mediaType: String,
        timeWindow: String,
        apiKey: String
    ): Flowable<MoviesEntity>

    fun getLocalMovies(
        mediaType: String,
        timeWindow: String,
        apiKey: String
    ): Flowable<MoviesEntity>

    fun getRemoteMovies(
        mediaType: String,
        timeWindow: String,
        apiKey: String
    ): Flowable<MoviesEntity>
}