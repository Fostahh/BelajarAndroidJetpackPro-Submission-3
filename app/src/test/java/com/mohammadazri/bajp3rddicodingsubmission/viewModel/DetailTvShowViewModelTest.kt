package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow.TvShowRepositoryInterface
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalArgumentException

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TvShowRepositoryInterface

    @Mock
    private lateinit var observer: Observer<Resource<TvShowEntity>>

    @Before
    fun setup() {
        detailTvShowViewModel = DetailTvShowViewModel(repository)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = Resource.success(dummyTvShow)

        tvShowId?.let {
            `when`(repository.getTvShowById(it)).thenReturn(tvShow)
            val tvShowFromViewModel = detailTvShowViewModel.getTvShowById(tvShowId).value
            val tvRepository = repository.getTvShowById(tvShowId).value
            assertNotNull(tvShowFromViewModel)
            assertEquals(tvShowFromViewModel?.data?.tvShowId, tvRepository?.data?.tvShowId)

            detailTvShowViewModel.getTvShowById(tvShowId).observeForever(observer)
            verify(observer).onChanged(tvRepository)
        } ?: run {
            throw IllegalArgumentException("Test failed")
        }
    }
}