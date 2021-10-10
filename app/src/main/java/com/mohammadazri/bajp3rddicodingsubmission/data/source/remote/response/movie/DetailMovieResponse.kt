package com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.GenreResponse

data class DetailMovieResponse(
    val id: Int?,

    val title: String?,

    @SerializedName("vote_average")
    val rating: Double?,

    @SerializedName("release_date")
    val releaseDate: String?,

    val overview: String?,

    @SerializedName("poster_path")
    val image: String?,

    val genres: List<GenreResponse>?,

    val runtime: Int?
)