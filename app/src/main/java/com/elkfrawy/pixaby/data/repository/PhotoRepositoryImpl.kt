package com.elkfrawy.pixaby.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elkfrawy.pixaby.data.remote.Api
import com.elkfrawy.pixaby.data.remote.photo.PhotoPagingSource
import com.elkfrawy.pixaby.data.remote.photo.PhotoRemoteSource
import com.elkfrawy.pixaby.data.utils.PAGE_SIZE
import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(val api: Api, val photoRemoteSource: PhotoRemoteSource) :
    PhotoRepository {

    override fun getPhotos(text: String): Flow<PagingData<Photo>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                prefetchDistance = 40
            ),
            pagingSourceFactory =  {PhotoPagingSource(text, api)}
        ).flow
    }

    override suspend fun getPhotoById(id: Int): Result<Photo> = photoRemoteSource.getPhotoById(id)
}