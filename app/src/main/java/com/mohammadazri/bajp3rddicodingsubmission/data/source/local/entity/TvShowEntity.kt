package com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritetvshow")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: Int?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "rating")
    var rating: Double?,

    @ColumnInfo(name = "image")
    var image: String?,

    @ColumnInfo(name = "genres")
    var genres: String?,

    @ColumnInfo(name = "episodes")
    val episodes: Int?,

    @ColumnInfo(name = "seasons")
    val seasons: Int?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false
)