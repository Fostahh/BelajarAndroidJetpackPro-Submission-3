package com.mohammadazri.bajp3rddicodingsubmission.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.room.MovieTvShowDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource constructor(private val movieTvShowDao: MovieTvShowDao) : ILocalDataSource {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieTvShowDao: MovieTvShowDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieTvShowDao)
            }
    }

    override fun insertMovies(movies: List<MovieEntity>) = movieTvShowDao.insertMovies(movies)

    override fun getMovies(): DataSource.Factory<Int, MovieEntity> = movieTvShowDao.getMovies()

    override fun insertDetailMovie(movie: MovieEntity) = movieTvShowDao.insertDetailMovie(movie)

    override fun getMovieById(id: Int): LiveData<MovieEntity> = movieTvShowDao.getMovieById(id)

    override fun getFavoritedMovie(): DataSource.Factory<Int, MovieEntity> =
        movieTvShowDao.getFavoritedMovie()

    override fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieTvShowDao.setFavoriteMovie(movie)
    }

    override fun insertTvShows(tvShows: List<TvShowEntity>) = movieTvShowDao.insertTvShows(tvShows)

    override fun getTvShows(): DataSource.Factory<Int, TvShowEntity> = movieTvShowDao.getTvShows()

    override fun insertDetailTvShow(tvShow: TvShowEntity) =
        movieTvShowDao.insertDetailTvShow(tvShow)

    override fun getTvShowById(id: Int): LiveData<TvShowEntity> = movieTvShowDao.getTvShowById(id)

    override fun getFavoritedTvShow(): DataSource.Factory<Int, TvShowEntity> =
        movieTvShowDao.getFavoritedTvShow()

    override fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFavorite = newState
        movieTvShowDao.setFavoriteTvShow(tvShow)
    }
}