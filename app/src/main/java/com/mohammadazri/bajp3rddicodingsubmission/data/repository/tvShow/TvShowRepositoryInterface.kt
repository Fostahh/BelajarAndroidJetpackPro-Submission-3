package com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.RepositoryInterface

interface TvShowRepositoryInterface : RepositoryInterface {
    fun getTvShow(page: Int): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShowById(id: Int): LiveData<Resource<TvShowEntity>>
    fun getFavoritedTvShow(): LiveData<PagedList<TvShowEntity>>
    fun setFavoriteMovie(tvShow: TvShowEntity, newState: Boolean)
}