package com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.GenreResponse

data class DetailTvShowResponse(
    val id: Int?,

    @SerializedName("name")
    val title: String?,

    @SerializedName("first_air_date")
    val firstAirDate: String?,

    val overview: String?,

    @SerializedName("vote_average")
    val rating: Double?,

    @SerializedName("poster_path")
    val image: String?,

    val genres: List<GenreResponse>?,

    @SerializedName("number_of_episodes")
    val episodes: Int?,

    @SerializedName("number_of_seasons")
    val seasons: Int?
)