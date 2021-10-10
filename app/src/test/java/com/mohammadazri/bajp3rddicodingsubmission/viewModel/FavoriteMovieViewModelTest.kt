package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.movie.MovieRepositoryInterface
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepositoryInterface

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setup() {
        viewModel = FavoriteMovieViewModel(repository)
    }

    @Test
    fun getFavoriteMovie() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(5)

        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(repository.getFavoritedMovie()).thenReturn(movies)
        val moviesFromViewModel = viewModel.getFavoriteMovie().value
        val moviesFromRepository = repository.getFavoritedMovie().value
        assertNotNull(moviesFromViewModel)
        assertNotNull(moviesFromRepository)
        assertEquals(moviesFromRepository?.size, moviesFromViewModel?.size)

        viewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}