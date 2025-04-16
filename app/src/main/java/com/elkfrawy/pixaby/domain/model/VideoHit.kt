package com.elkfrawy.pixaby.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoHit(
    val id: Int,
    val duration: Int,
    val picture_id: String,
    val videos: Videos,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user_id: Int,
    val user: String,
    val userImageURL: String,
):Parcelable
