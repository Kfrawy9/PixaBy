package com.elkfrawy.pixaby.data.remote.video

import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.domain.model.VideoHit

interface VideoRemoteSource {

    suspend fun getVideoById(id: Int): Result<VideoHit>

}