package com.mohammadazri.bajp3rddicodingsubmission.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mohammadazri.bajp3rddicodingsubmission.BuildConfig
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.api.ApiConfig
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie.DetailMovieResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie.MovieBodyResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie.MovieResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.tvshow.DetailTvShowResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.tvshow.TvShowBodyResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.tvshow.TvShowResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.vo.ApiResponse
import com.mohammadazri.bajp3rddicodingsubmission.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RemoteDataSource : IRemoteDataSource {
    private val TAG = "RemoteDataSource"

    override fun getMovie(page: Int): LiveData<ApiResponse<List<MovieResponse>>> {
        val movieResult = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        EspressoIdlingResource.increment()
        val client = ApiConfig.apiInstance.getMovie(BuildConfig.APIKey, page)
        client.enqueue(object : Callback<MovieBodyResponse> {
            override fun onResponse(
                call: Call<MovieBodyResponse>, response: Response<MovieBodyResponse>
            ) {
                val movies = response.body()?.results
                movieResult.value = try {
                    if (movies != null) ApiResponse.success(movies) else ApiResponse.empty(
                        "Data Empty",
                        movies
                    )
                } catch (e: IOException) {
                    e.printStackTrace()
                    ApiResponse.error("There is an error", movies)
                }

                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieBodyResponse>, t: Throwable) {
                Log.d(TAG, t.localizedMessage as String)
            }
        })
        return movieResult
    }

    override fun getDetailMovie(id: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        val detailMovieResult = MutableLiveData<ApiResponse<DetailMovieResponse>>()

        EspressoIdlingResource.increment()
        val client = ApiConfig.apiInstance.getDetailMovie(id, BuildConfig.APIKey)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                val detailMovie = response.body()
                detailMovieResult.value = try {
                    if (detailMovie != null) ApiResponse.success(detailMovie) else ApiResponse.empty(
                        "Data Empty",
                        detailMovie
                    )
                } catch (e: IOException) {
                    e.printStackTrace()
                    ApiResponse.error("There is an error", detailMovie)
                }

                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.d(TAG, t.localizedMessage as String)
            }
        })

        return detailMovieResult
    }

    override fun getTvShow(page: Int): LiveData<ApiResponse<List<TvShowResponse>>> {
        val tvShowResult = MutableLiveData<ApiResponse<List<TvShowResponse>>>()

        EspressoIdlingResource.increment()
        val client = ApiConfig.apiInstance.getTvShow(BuildConfig.APIKey, page)
        client.enqueue(object : Callback<TvShowBodyResponse> {
            override fun onResponse(
                call: Call<TvShowBodyResponse>,
                response: Response<TvShowBodyResponse>
            ) {
                val tvShows = response.body()?.results
                tvShowResult.value = try {
                    if (tvShows != null) ApiResponse.success(tvShows) else ApiResponse.empty(
                        "Data Empty",
                        tvShows
                    )
                } catch (e: IOException) {
                    e.printStackTrace()
                    ApiResponse.error("There is an error", tvShows)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowBodyResponse>, t: Throwable) {
                Log.d(TAG, t.localizedMessage as String)
            }
        })

        return tvShowResult
    }

    override fun getDetailTvShow(id: Int): LiveData<ApiResponse<DetailTvShowResponse>> {
        val detailTvShowResult = MutableLiveData<ApiResponse<DetailTvShowResponse>>()

        EspressoIdlingResource.increment()
        val client = ApiConfig.apiInstance.getDetailTvShow(id, BuildConfig.APIKey)
        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(
                call: Call<DetailTvShowResponse>,
                response: Response<DetailTvShowResponse>
            ) {
                val detailTvShow = response.body()
                detailTvShowResult.value = try {
                    if (detailTvShow != null) ApiResponse.success(detailTvShow) else ApiResponse.empty(
                        "Data Empty",
                        detailTvShow
                    )
                } catch (e: IOException) {
                    e.printStackTrace()
                    ApiResponse.error("There is an error", detailTvShow)
                }

                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.d(TAG, t.localizedMessage as String)
            }
        })

        return detailTvShowResult
    }


}