package com.elkfrawy.pixaby.data.remote

import com.elkfrawy.pixaby.data.model.PhotoResponse
import com.elkfrawy.pixaby.data.model.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("api/")
    suspend fun getPhotos(@Query("q") text: String, @Query("page") page: Int): Response<PhotoResponse>

    @GET("api/")
    suspend fun getPhotoById(@Query("id") id: Int): Response<PhotoResponse>


    @GET("api/videos/")
    suspend fun getVideos(@Query("q") text: String, @Query("page") page: Int): Response<VideoResponse>


    @GET("api/videos/")
    suspend fun getVideoById(@Query("id") id: Int): Response<VideoResponse>

}