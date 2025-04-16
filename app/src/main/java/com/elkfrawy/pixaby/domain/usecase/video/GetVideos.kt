package com.elkfrawy.pixaby.domain.usecase.video

import androidx.paging.PagingData
import com.elkfrawy.pixaby.domain.model.VideoHit
import com.elkfrawy.pixaby.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideos @Inject constructor(val videoRepo: VideoRepository) {

     fun execute(text: String):Flow<PagingData<VideoHit>> =
        videoRepo.getVideos(text)

}