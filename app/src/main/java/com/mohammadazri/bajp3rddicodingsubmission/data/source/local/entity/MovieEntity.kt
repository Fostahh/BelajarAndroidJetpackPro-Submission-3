package com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritemovie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "rating")
    var rating: Double?,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "image")
    var image: String?,

    @ColumnInfo(name = "genres")
    var genres: String?,

    @ColumnInfo(name = "runtime")
    val runtime: Int?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false
)
