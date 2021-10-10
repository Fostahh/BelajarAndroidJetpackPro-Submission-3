package com.mohammadazri.bajp3rddicodingsubmission.data.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.source.NetworkBoundResource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.ILocalDataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.IRemoteDataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie.DetailMovieResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.response.movie.MovieResponse
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.vo.ApiResponse
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MovieRepository(
    private val remoteDataSource: IRemoteDataSource,
    private val localDataSource: ILocalDataSource
) : MovieRepositoryInterface {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    override fun getMovies(page: Int): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>() {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }


            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie(page)

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieEntities = data.map {
                    MovieEntity(
                        it.id,
                        it.title,
                        it.rating,
                        it.releaseDate,
                        it.overview,
                        it.image,
                        null,
                        null,
                        false
                    )
                }
                localDataSource.insertMovies(movieEntities)
            }

        }.asLiveData()
    }

    override fun getMovieById(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>() {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovieById(id)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data?.genres == null || data.runtime == null

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(id)

            override fun saveCallResult(data: DetailMovieResponse) {
                val genres = data.genres?.joinToString(", ") { it.name }
                val movieEntity = MovieEntity(
                    data.id,
                    data.title,
                    data.rating,
                    data.releaseDate,
                    data.overview,
                    data.image,
                    genres,
                    data.runtime,
                    false
                )
                localDataSource.insertDetailMovie(movieEntity)
            }
        }.asLiveData()
    }

    override fun getFavoritedMovie(): LiveData<PagedList<MovieEntity>> {
        val config =
            PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(4)
                .setPageSize(4).build()
        return LivePagedListBuilder(localDataSource.getFavoritedMovie(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) =
        executorService.execute {
            localDataSource.setFavoriteMovie(movie, newState)
        }
}
