package com.mohammadazri.bajp3rddicodingsubmission.di

import android.content.Context
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.LocalDataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.room.MovieTvShowDatabase
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.RemoteDataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.movie.MovieRepository
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow.TvShowRepository

object Injection {
    fun provideMovieRepository(context: Context): MovieRepository {
        val database = MovieTvShowDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.movieTvShowDao())

        return MovieRepository(RemoteDataSource(), localDataSource)
    }

    fun provideDetailMovieRepository(context: Context): MovieRepository {
        val database = MovieTvShowDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.movieTvShowDao())

        return MovieRepository(RemoteDataSource(), localDataSource)
    }

    fun provideTvShowRepository(context: Context): TvShowRepository {
        val database = MovieTvShowDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.movieTvShowDao())

        return TvShowRepository(RemoteDataSource(), localDataSource)
    }

    fun provideDetailTvShowRepository(context: Context): TvShowRepository {
        val database = MovieTvShowDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.movieTvShowDao())

        return TvShowRepository(RemoteDataSource(), localDataSource)
    }
}