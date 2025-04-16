package com.elkfrawy.pixaby.di

import com.elkfrawy.pixaby.data.remote.photo.PhotoRemoteSource
import com.elkfrawy.pixaby.data.remote.photo.PhotoRemoteSourceImpl
import com.elkfrawy.pixaby.data.remote.video.VideoRemoteSource
import com.elkfrawy.pixaby.data.remote.video.VideoRemoteSourceImpl
import com.elkfrawy.pixaby.data.repository.PhotoRepositoryImpl
import com.elkfrawy.pixaby.data.repository.VideoRepositoryImpl
import com.elkfrawy.pixaby.domain.repository.PhotoRepository
import com.elkfrawy.pixaby.domain.repository.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class InterfaceModule {


    @Singleton
    @Binds
    abstract fun providePhotoRemoteSource(photoRemote: PhotoRemoteSourceImpl): PhotoRemoteSource

    @Singleton
    @Binds
    abstract fun provideVideoRemoteSource(videoRemote: VideoRemoteSourceImpl): VideoRemoteSource

    @Singleton
    @Binds
    abstract fun provideVideoRepo(videoRepo: VideoRepositoryImpl): VideoRepository

    @Singleton
    @Binds
    abstract fun providePhotoRepo(photoRepo: PhotoRepositoryImpl): PhotoRepository

}