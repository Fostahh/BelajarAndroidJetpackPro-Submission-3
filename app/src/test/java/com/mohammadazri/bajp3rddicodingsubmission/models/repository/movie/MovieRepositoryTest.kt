package com.mohammadazri.bajp3rddicodingsubmission.models.repository.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.movie.MovieRepository
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.ILocalDataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.source.remote.IRemoteDataSource
import com.mohammadazri.bajp3rddicodingsubmission.utils.DataDummy
import com.mohammadazri.bajp3rddicodingsubmission.utils.LiveDataTestUtil
import com.mohammadazri.bajp3rddicodingsubmission.utils.PagedListUtil
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MovieRepositoryTest {

    private lateinit var movieRepository: MovieRepository

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val movieId = dummyMovies[0].movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteDataSource: IRemoteDataSource

    @Mock
    private lateinit var localDataSource: ILocalDataSource

    @Before
    fun setup() {
        movieRepository = MovieRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun getMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getMovies()).thenReturn(dataSourceFactory)
        movieRepository.getMovies(1)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(dummyMovies))

        verify(localDataSource).getMovies()
        assertNotNull(movieEntities.data)
        assertEquals(dummyMovies.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovies[0]
        `when`(localDataSource.getMovieById(movieId as Int)).thenReturn(movie)

        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovieById(movieId))
        verify(localDataSource).getMovieById(movieId)
        assertNotNull(movieEntity.data)
        assertEquals(movieId, movieEntity.data?.movieId)
    }
}