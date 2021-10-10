package com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int?,

    val title: String?,

    @SerializedName("vote_average")
    val rating: Double?,

    @SerializedName("release_date")
    val releaseDate: String?,

    val overview: String?,

    @SerializedName("poster_path")
    val image: String?
)