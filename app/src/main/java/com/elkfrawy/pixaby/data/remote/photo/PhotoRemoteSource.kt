package com.elkfrawy.pixaby.data.remote.photo

import androidx.paging.PagingSource
import com.elkfrawy.pixaby.data.model.PhotoResponse
import com.elkfrawy.pixaby.domain.model.Photo

interface PhotoRemoteSource {

    suspend fun getPhotoById(id: Int): Result<Photo>

}