package com.elkfrawy.pixaby.domain.repository

import androidx.paging.PagingData
import com.elkfrawy.pixaby.domain.model.VideoHit
import kotlinx.coroutines.flow.Flow

interface VideoRepository {

    fun getVideos(text: String): Flow<PagingData<VideoHit>>
    suspend fun getVideoById(id: Int): Result<VideoHit>

}