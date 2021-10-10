package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.movie.MovieRepositoryInterface


class MovieViewModel(private val repository: MovieRepositoryInterface) : ViewModel() {
    fun getMovies(page: Int): LiveData<Resource<PagedList<MovieEntity>>> = repository.getMovies(page)
}