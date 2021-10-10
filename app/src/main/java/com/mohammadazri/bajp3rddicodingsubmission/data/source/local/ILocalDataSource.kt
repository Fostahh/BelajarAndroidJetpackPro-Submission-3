package com.mohammadazri.bajp3rddicodingsubmission.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity

interface ILocalDataSource {

    fun insertMovies(movies: List<MovieEntity>)

    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    fun insertDetailMovie(movie: MovieEntity)

    fun getMovieById(id: Int): LiveData<MovieEntity>

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean)

    fun getFavoritedMovie(): DataSource.Factory<Int, MovieEntity>

    fun insertTvShows(tvShows: List<TvShowEntity>)

    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    fun insertDetailTvShow(tvShow: TvShowEntity)

    fun getTvShowById(id: Int): LiveData<TvShowEntity>

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean)

    fun getFavoritedTvShow(): DataSource.Factory<Int, TvShowEntity>
}