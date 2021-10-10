package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.movie.MovieRepositoryInterface

class FavoriteMovieViewModel(private val repository: MovieRepositoryInterface) : ViewModel() {
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> = repository.getFavoritedMovie()

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) =
        repository.setFavoriteMovie(movie, newState)
}