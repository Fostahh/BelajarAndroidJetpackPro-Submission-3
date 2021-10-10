package com.mohammadazri.bajp3rddicodingsubmission.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int?,
    val title: String?,
    val firstAirDate: String?,
    val overview: String?,
    val rating: Double?,
    val image: String?,
    val genres: List<Genre>?,
    val episodes: Int?,
    val seasons: Int?
) : Parcelable
