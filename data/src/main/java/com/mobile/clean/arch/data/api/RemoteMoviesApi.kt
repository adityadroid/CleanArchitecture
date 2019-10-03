package com.mobile.clean.arch.data.api

import com.mobile.clean.arch.domain.MoviesList
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteMoviesApi {

    @GET("/trending/{media_type}/{time_window}?api_key={api_key}")
    fun trendingMoviewList(
        @Path("media_type") mediaType: String, @Path("time_window") timeWindow: String, @Path("api_key") apiKey: String
    ): Flowable<MoviesList>

}
 