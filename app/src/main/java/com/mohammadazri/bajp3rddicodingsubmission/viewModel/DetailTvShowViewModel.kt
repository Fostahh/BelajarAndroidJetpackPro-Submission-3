package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.models.TvShow
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow.TvShowRepositoryInterface

class DetailTvShowViewModel(private val repository: TvShowRepositoryInterface) : ViewModel() {
    fun getTvShowById(id: Int): LiveData<Resource<TvShowEntity>> = repository.getTvShowById(id)

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) = repository.setFavoriteMovie(tvShow, newState)
}