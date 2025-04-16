package com.elkfrawy.pixaby.domain.usecase.photo

import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhoto @Inject constructor(val photoRepo: PhotoRepository) {

    suspend fun execute(id: Int):Result<Photo> =
        photoRepo.getPhotoById(id)

}