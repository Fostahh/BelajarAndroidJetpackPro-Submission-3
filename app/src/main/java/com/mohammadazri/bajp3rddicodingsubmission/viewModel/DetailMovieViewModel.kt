package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.movie.MovieRepositoryInterface

class DetailMovieViewModel(private val repository: MovieRepositoryInterface) : ViewModel() {
    fun getMovieById(id: Int): LiveData<Resource<MovieEntity>> = repository.getMovieById(id)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) =
        repository.setFavoriteMovie(movie, newState)
}