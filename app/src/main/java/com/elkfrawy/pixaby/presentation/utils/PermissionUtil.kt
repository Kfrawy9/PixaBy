package com.elkfrawy.pixaby.presentation.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager

fun Context.permissionGranted(): Boolean {
    return this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
}