package com.mohammadazri.bajp3rddicodingsubmission.models.repository.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow.TvShowRepository
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.ILocalDataSource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
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
class TvShowRepositoryTest {

    private lateinit var tvShowRepository: TvShowRepository

    private val dummyTvShows = DataDummy.generateDummyTvShows()
    private val tvShowId = dummyTvShows[0].tvShowId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteDataSource: IRemoteDataSource

    @Mock
    private lateinit var localDataSource: ILocalDataSource

    @Before
    fun setup() {
        tvShowRepository = TvShowRepository(remoteDataSource, localDataSource)
    }


    @Test
    fun getTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(localDataSource.getTvShows()).thenReturn(dataSourceFactory)
        tvShowRepository.getTvShow(1)

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(dummyTvShows))
        verify(localDataSource).getTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(dummyTvShows.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<TvShowEntity>()
        tvShow.value = dummyTvShows[0]
        `when`(localDataSource.getTvShowById(tvShowId as Int)).thenReturn(tvShow)

        val tvShowEntity = LiveDataTestUtil.getValue(tvShowRepository.getTvShowById(tvShowId))
        verify(localDataSource).getTvShowById(tvShowId)
        assertNotNull(tvShowEntity.data)
        assertEquals(tvShowId, tvShowEntity.data?.tvShowId)

    }
}