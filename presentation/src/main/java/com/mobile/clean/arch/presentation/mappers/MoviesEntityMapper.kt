package com.mobile.clean.arch.presentation.mappers


import com.mobile.clean.arch.domain.common.Mapper
import com.mobile.clean.arch.domain.entities.MoviesEntity
import com.mobile.clean.arch.domain.entities.MoviesItemEntity
import com.mobile.clean.arch.presentation.entities.Movies
import com.mobile.clean.arch.presentation.entities.MoviesItem

class MoviesEntityMapper : Mapper<MoviesEntity, Movies>() {

    override fun mapFrom(from: MoviesEntity): Movies = Movies(
        page = from.page,
        results = mapListMoviesToPresentation(from.results)
    )

    private fun mapListMoviesToPresentation(movies: List<MoviesItemEntity>?):
            List<MoviesItem> = movies?.map { mapMoviesToPresentation(it) } ?: emptyList()


    private fun mapMoviesToPresentation(response: MoviesItemEntity): MoviesItem = MoviesItem(
        vote_count = response.vote_count,
        vote_average = response.vote_average,
        title = response.title,
        release_date = response.release_date,
        poster_path = response.poster_path
    )

}