package com.mobile.clean.arch.domain.repositories

import com.mobile.clean.arch.domain.entities.MoviesEntity
import io.reactivex.Flowable

interface MoviesRepository {

    fun getMovies(): Flowable<MoviesEntity>

}