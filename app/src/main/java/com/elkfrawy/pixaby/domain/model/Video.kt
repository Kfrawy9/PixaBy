package com.elkfrawy.pixaby.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video (
    val url: String,
    val size: Int,
): Parcelable