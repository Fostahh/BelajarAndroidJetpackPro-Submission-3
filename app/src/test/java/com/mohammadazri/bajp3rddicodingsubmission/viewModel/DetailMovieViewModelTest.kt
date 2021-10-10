package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.movie.MovieRepositoryInterface
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepositoryInterface

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @Before
    fun setup() {
        detailMovieViewModel = DetailMovieViewModel(repository)
    }

    @Test
    fun getMovieById() {
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = Resource.success(dummyMovie)

        movieId?.let {
            `when`(repository.getMovieById(movieId)).thenReturn(movie)
            val movieFromViewModel = detailMovieViewModel.getMovieById(it).value
            val movieFromRepository = repository.getMovieById(it).value
            assertNotNull(movieFromViewModel)
            assertEquals(movieFromViewModel?.data?.movieId, movieFromRepository?.data?.movieId)

            detailMovieViewModel.getMovieById(movieId).observeForever(observer)
            verify(observer).onChanged(movieFromRepository)

        } ?: run {
            throw IllegalArgumentException("Test failed")
        }
    }
}