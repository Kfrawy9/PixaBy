package com.elkfrawy.pixaby.domain.usecase.photo

import androidx.paging.PagingData
import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotos @Inject constructor(val photoRepo: PhotoRepository) {

     fun execute(text: String): Flow<PagingData<Photo>> =
        photoRepo.getPhotos(text)

}