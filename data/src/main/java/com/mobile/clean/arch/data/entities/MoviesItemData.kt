package com.mobile.clean.arch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MoviesItemData(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("vote_count") var vote_count: Int,
    @SerializedName("vote_average") var vote_average: Double,
    @SerializedName("title") var title: String? = null,
    @SerializedName("release_date") var release_date: String? = null,
    @SerializedName("poster_path") var poster_path: String? = null
)
