package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow.TvShowRepositoryInterface
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
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
class FavoriteTvShowViewModelTest {

    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TvShowRepositoryInterface

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup() {
        viewModel = FavoriteTvShowViewModel(repository)
    }

    @Test
    fun getFavoriteTvShow() {
        val dummyTvShows = pagedList
        `when`(dummyTvShows.size).thenReturn(5)

        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(repository.getFavoritedTvShow()).thenReturn(tvShows)
        val tvShowsFromViewModel = viewModel.getFavoriteTvShow().value
        val tvShowsFromRepository = repository.getFavoritedTvShow().value
        assertNotNull(tvShowsFromViewModel)
        assertNotNull(tvShowsFromRepository)
        assertEquals(tvShowsFromRepository?.size, tvShowsFromViewModel?.size)

        viewModel.getFavoriteTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }


}