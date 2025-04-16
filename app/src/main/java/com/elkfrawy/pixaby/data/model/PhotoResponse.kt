package com.elkfrawy.pixaby.data.model

import com.elkfrawy.pixaby.domain.model.Photo

data class PhotoResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<Photo>,
)
