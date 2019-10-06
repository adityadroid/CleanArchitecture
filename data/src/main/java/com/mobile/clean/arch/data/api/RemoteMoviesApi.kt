package com.mobile.clean.arch.data.api

import com.mobile.clean.arch.data.entities.MoviesData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteMoviesApi {

    @GET("/3/trending/{media_type}/{time_window}")
    fun getMovieList(
        @Path("media_type") mediaType: String, @Path("time_window") timeWindow: String
    ): Flowable<MoviesData>

}
 