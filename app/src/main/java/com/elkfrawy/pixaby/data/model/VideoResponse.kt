package com.elkfrawy.pixaby.data.model

import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.domain.model.VideoHit

data class VideoResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<VideoHit>,
)
