package com.elkfrawy.pixaby.domain.service

import android.app.Notification
import android.content.Context
import android.os.Environment
import androidx.core.app.NotificationCompat
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.cache.NoOpCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.exoplayer.offline.Download
import androidx.media3.exoplayer.offline.DownloadManager
import androidx.media3.exoplayer.offline.DownloadService
import androidx.media3.exoplayer.scheduler.Scheduler
import com.elkfrawy.pixaby.R
import com.elkfrawy.pixaby.data.utils.CHANNEL_ID
import com.elkfrawy.pixaby.data.utils.NOTIFICATION_ID
import java.util.concurrent.Executor

@UnstableApi class DownloadMedia(): DownloadService(NOTIFICATION_ID) {

    override fun getDownloadManager(): DownloadManager {
        val database = StandaloneDatabaseProvider(this)
        val cache = SimpleCache(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), NoOpCacheEvictor(), database)
        val source = DefaultHttpDataSource.Factory()
        val executor = Executor(Runnable::run)

        val manager = DownloadManager(this, database, cache, source, executor)
        manager.maxParallelDownloads = 2

        return manager
    }

    override fun getScheduler(): Scheduler? {
        return null
    }

    override fun getForegroundNotification(downloads: MutableList<Download>, notMetRequirements: Int): Notification {

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Downloading File.....")
            .setAutoCancel(false)
            .setSmallIcon(R.drawable.baseline_download)
            .build()

        return notification
    }
}