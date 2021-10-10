package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.models.TvShow
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow.TvShowRepositoryInterface

class TvShowViewModel(private val repository: TvShowRepositoryInterface) : ViewModel() {
    fun getTvShows(page: Int): LiveData<Resource<PagedList<TvShowEntity>>> = repository.getTvShow(page)
}