package com.mohammadazri.bajp3rddicodingsubmission.data.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.RepositoryInterface

interface MovieRepositoryInterface : RepositoryInterface {
    fun getMovies(page: Int): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieById(id: Int): LiveData<Resource<MovieEntity>>
    fun getFavoritedMovie(): LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean)
}