package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow.TvShowRepositoryInterface
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TvShowRepositoryInterface

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup() {
        tvShowViewModel = TvShowViewModel(repository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(5)

        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(repository.getTvShow(1)).thenReturn(tvShows)
        val tvShowsFromViewModel = tvShowViewModel.getTvShows(1).value?.data
        val tvShowsFromRepository = repository.getTvShow(1).value
        assertNotNull(tvShowsFromViewModel)
        assertEquals(tvShowsFromRepository?.data?.size, tvShowsFromViewModel?.size)

        tvShowViewModel.getTvShows(1).observeForever(observer)
        verify(observer).onChanged(tvShowsFromRepository)
    }
}