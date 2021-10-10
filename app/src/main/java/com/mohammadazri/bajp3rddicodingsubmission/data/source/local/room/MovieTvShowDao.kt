package com.mohammadazri.bajp3rddicodingsubmission.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity

@Dao
interface MovieTvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM favoritemovie ORDER BY rating DESC")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovie(movie: MovieEntity)

    @Query("SELECT * FROM favoritemovie where movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Update
    fun setFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM favoritemovie where isFavorite = 1 ORDER BY rating DESC")
    fun getFavoritedMovie(): DataSource.Factory<Int, MovieEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Query("SELECT * FROM favoritetvshow ORDER BY rating DESC")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM favoritetvshow where tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity>

    @Update
    fun setFavoriteTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM favoritetvshow where isFavorite = 1 ORDER BY rating DESC")
    fun getFavoritedTvShow(): DataSource.Factory<Int, TvShowEntity>
}