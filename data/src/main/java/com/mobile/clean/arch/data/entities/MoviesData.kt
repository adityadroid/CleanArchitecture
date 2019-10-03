package com.mobile.clean.arch.data.entities

import com.google.gson.annotations.SerializedName
import com.mobile.clean.arch.domain.entities.MoviesEntity
import com.mobile.clean.arch.domain.entities.MoviesItemEntity

data class MoviesData(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<MoviesItemData> = emptyList()
)

class MoviesDataEntityMapper constructor() {

    fun mapToEntity(data: MoviesData?): MoviesEntity? = MoviesEntity(
        page = data!!.page,
        results = mapListMoviesToEntity(data.results)
    )

    fun mapToEntity(movies: List<MoviesItemData>?): MoviesEntity? = MoviesEntity(
        results = mapListMoviesToEntity(movies)
    )

    fun mapListMoviesToEntity(movies: List<MoviesItemData>?)
            : List<MoviesItemEntity> = movies?.map { mapMoviesToEntity(it) } ?: emptyList()

    fun mapMoviesToEntity(response: MoviesItemData): MoviesItemEntity = MoviesItemEntity(
        vote_count = response.vote_count,
        vote_average = response.vote_average,
        title = response.title.toString(),
        release_date = response.release_date.toString(),
        poster_path = response.poster_path.toString()
    )
}

class MoviesEntityDataMapper constructor() {

    fun mapToEntity(data: MoviesEntity?): MoviesData? = MoviesData(
        page = data!!.page,
        results = mapListMoviesToEntity(data.results)
    )

    fun mapListMoviesToEntity(movies: List<MoviesItemEntity>?):
            List<MoviesItemData> = movies?.map { mapMoviesToEntity(it) } ?: emptyList()


    fun mapMoviesToEntity(response: MoviesItemEntity): MoviesItemData = MoviesItemData(
        vote_count = response.vote_count,
        vote_average = response.vote_average,
        title = response.title,
        release_date = response.release_date,
        poster_path = response.poster_path

    )
}









