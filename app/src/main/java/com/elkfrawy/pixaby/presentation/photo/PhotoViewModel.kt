package com.elkfrawy.pixaby.presentation.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.domain.usecase.photo.GetPhoto
import com.elkfrawy.pixaby.domain.usecase.photo.GetPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(val getPhotosUseCase: GetPhotos, val getPhotoUseCase: GetPhoto) :
    ViewModel() {

    lateinit var photo: Photo

    fun getPhotos(text: String): Flow<PagingData<Photo>>{
        return getPhotosUseCase.execute(text).cachedIn(viewModelScope)
    }

}