package com.elkfrawy.pixaby.domain.usecase.video

import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.domain.model.VideoHit
import com.elkfrawy.pixaby.domain.repository.PhotoRepository
import com.elkfrawy.pixaby.domain.repository.VideoRepository
import javax.inject.Inject

class GetVideo @Inject constructor(val videoRepo: VideoRepository) {

    suspend fun execute(id: Int):Result<VideoHit> =
        videoRepo.getVideoById(id)

}