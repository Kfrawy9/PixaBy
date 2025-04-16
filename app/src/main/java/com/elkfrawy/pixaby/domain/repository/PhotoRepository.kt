package com.elkfrawy.pixaby.domain.repository

import androidx.paging.PagingData
import com.elkfrawy.pixaby.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(text: String): Flow<PagingData<Photo>>
    suspend fun getPhotoById(id: Int): Result<Photo>

}