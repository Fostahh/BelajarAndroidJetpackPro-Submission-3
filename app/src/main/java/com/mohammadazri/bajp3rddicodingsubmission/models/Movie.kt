package com.mohammadazri.bajp3rddicodingsubmission.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int?,
    val title: String?,
    val rating: Double?,
    val releaseDate: String?,
    val overview: String?,
    val image: String?,
    val genres: List<Genre>,
    val runtime: Int
) : Parcelable