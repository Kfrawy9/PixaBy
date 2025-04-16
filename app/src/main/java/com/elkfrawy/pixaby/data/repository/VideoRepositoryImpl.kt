package com.elkfrawy.pixaby.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elkfrawy.pixaby.data.remote.Api
import com.elkfrawy.pixaby.data.remote.video.VideoPagingSource
import com.elkfrawy.pixaby.data.remote.video.VideoRemoteSource
import com.elkfrawy.pixaby.data.utils.PAGE_SIZE
import com.elkfrawy.pixaby.data.utils.PAGE_SIZE_VIDEO
import com.elkfrawy.pixaby.domain.model.VideoHit
import com.elkfrawy.pixaby.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(val api: Api, val videoRemoteSource: VideoRemoteSource) :
    VideoRepository {

    override fun getVideos(text: String): Flow<PagingData<VideoHit>> {
        return Pager(PagingConfig(
            pageSize = PAGE_SIZE_VIDEO,
            prefetchDistance = 40,
            enablePlaceholders = false,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED
            ),
            pagingSourceFactory = {VideoPagingSource(text, api)}
        ).flow
    }

    override suspend fun getVideoById(id: Int): Result<VideoHit> =
        videoRemoteSource.getVideoById(id)
}