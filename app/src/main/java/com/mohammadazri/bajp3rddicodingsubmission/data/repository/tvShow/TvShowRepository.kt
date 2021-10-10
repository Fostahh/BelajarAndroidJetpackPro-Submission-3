package com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.source.NetworkBoundResource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.ILocalDataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.LocalDataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.IRemoteDataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.tvshow.DetailTvShowResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.tvshow.TvShowResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.vo.ApiResponse
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TvShowRepository(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: ILocalDataSource
) : TvShowRepositoryInterface {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    override fun getTvShow(page: Int): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShow(page)

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowEntities = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.title,
                        response.firstAirDate,
                        response.overview,
                        response.rating,
                        response.image,
                        null,
                        null,
                        null,
                        false
                    )
                    tvShowEntities.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowEntities)
            }
        }.asLiveData()
    }

    override fun getTvShowById(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvShowResponse>() {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShowById(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data?.genres == null || data.episodes == null || data.seasons == null

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> =
                remoteDataSource.getDetailTvShow(id)

            override fun saveCallResult(data: DetailTvShowResponse) {
                val genres = data.genres?.joinToString(", ") { it.name }
                val tvShowEntity = TvShowEntity(
                    data.id,
                    data.title,
                    data.firstAirDate,
                    data.overview,
                    data.rating,
                    data.image,
                    genres,
                    data.episodes,
                    data.seasons,
                    false
                )
                localDataSource.insertDetailTvShow(tvShowEntity)
            }

        }.asLiveData()
    }


    override fun getFavoritedTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(4).setPageSize(4).build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShow(), config).build()
    }

    override fun setFavoriteMovie(tvShow: TvShowEntity, newState: Boolean) {
        executorService.execute {
            localDataSource.setFavoriteTvShow(tvShow, newState)
        }
    }
}