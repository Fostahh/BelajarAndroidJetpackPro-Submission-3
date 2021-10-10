package com.mohammadazri.bajp3rddicodingsubmission.data.source.remote

import androidx.lifecycle.LiveData
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.vo.ApiResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie.DetailMovieResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie.MovieResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.tvshow.DetailTvShowResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.tvshow.TvShowResponse
import com.mohammadazri.bajp3rddicodingsubmission.models.TvShow

interface IRemoteDataSource {
    fun getMovie(page: Int): LiveData<ApiResponse<List<MovieResponse>>>
    fun getDetailMovie(id: Int): LiveData<ApiResponse<DetailMovieResponse>>
    fun getTvShow(page: Int): LiveData<ApiResponse<List<TvShowResponse>>>
    fun getDetailTvShow(id: Int): LiveData<ApiResponse<DetailTvShowResponse>>
}