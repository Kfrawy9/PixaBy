package com.elkfrawy.pixaby.domain.service

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class PixaDownload(private val context: Context): Downloader {

    val manager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long{
        val request = DownloadManager.Request(url.toUri())
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Environment.DIRECTORY_DCIM)

        return manager.enqueue(request)
    }
}