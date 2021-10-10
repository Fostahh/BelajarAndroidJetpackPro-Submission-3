package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow.TvShowRepositoryInterface

class FavoriteTvShowViewModel(private val repository: TvShowRepositoryInterface): ViewModel() {
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = repository.getFavoritedTvShow()

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean) = repository.setFavoriteMovie(tvShow, newState)
}