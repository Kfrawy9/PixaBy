package com.elkfrawy.pixaby.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Videos(
    val large: Video,
    val medium: Video,
    val small: Video,
    val tiny: Video,
):Parcelable
