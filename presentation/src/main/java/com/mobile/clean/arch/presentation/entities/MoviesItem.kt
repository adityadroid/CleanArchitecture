package com.mobile.clean.arch.presentation.entities


data class MoviesItem(
    var vote_count: Int,
    var vote_average: Double,
    var title: String,
    var release_date: String,
    var poster_path: String
)