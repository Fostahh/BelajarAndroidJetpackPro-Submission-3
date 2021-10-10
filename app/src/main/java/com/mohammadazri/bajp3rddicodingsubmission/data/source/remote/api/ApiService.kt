package com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.api

import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie.DetailMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie.MovieBodyResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.tvshow.DetailTvShowResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.tvshow.TvShowBodyResponse
import com.mohammadazri.bajp3rddicodingsubmission.models.TvShow
import retrofit2.Call
import retrofit2.http.Path

interface ApiService {

    @GET("movie/top_rated")
    fun getMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<MovieBodyResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>

    @GET("tv/top_rated")
    fun getTvShow(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<TvShowBodyResponse>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<DetailTvShowResponse>
}