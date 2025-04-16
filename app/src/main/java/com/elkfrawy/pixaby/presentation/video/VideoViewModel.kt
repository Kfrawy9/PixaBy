package com.elkfrawy.pixaby.presentation.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elkfrawy.pixaby.domain.model.VideoHit
import com.elkfrawy.pixaby.domain.usecase.video.GetVideo
import com.elkfrawy.pixaby.domain.usecase.video.GetVideos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val getVideosUseCase: GetVideos,
    private val getVideoUseCase: GetVideo,
) : ViewModel() {

    lateinit var video: VideoHit

    fun getVideo(text: String):Flow<PagingData<VideoHit>>{
        return getVideosUseCase.execute(text).cachedIn(viewModelScope)
    }


}