package com.elkfrawy.pixaby.presentation.video

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.offline.DownloadRequest
import androidx.media3.exoplayer.offline.DownloadService
import com.elkfrawy.pixaby.databinding.ActivityVideoDetailBinding
import com.elkfrawy.pixaby.domain.model.VideoHit
import com.elkfrawy.pixaby.domain.service.DownloadMedia
import com.elkfrawy.pixaby.domain.service.PixaDownload
import com.elkfrawy.pixaby.presentation.utils.DOWNLOAD_VIDEO_ID
import com.elkfrawy.pixaby.presentation.utils.VIDEO_DETAIL_KEY
import com.elkfrawy.pixaby.presentation.utils.hide
import com.elkfrawy.pixaby.presentation.utils.loadProfile
import com.elkfrawy.pixaby.presentation.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@UnstableApi class VideoDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityVideoDetailBinding
    lateinit var player: ExoPlayer
    val viewModel: VideoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player = ExoPlayer.Builder(this).build()
        binding.videoPlayer.player = player

        val intent = intent
        intent.extras?.let {
            val video = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(VIDEO_DETAIL_KEY, VideoHit::class.java)
            } else {
                it.getParcelable(VIDEO_DETAIL_KEY)
            }

            video?.let {
                putData(it)
                viewModel.video = it
            }
        }

        binding.downloadVid.setOnClickListener {
            PixaDownload(this).downloadFile(viewModel.video.videos.large.url)
        }


    }

    fun putData(video: VideoHit){

        binding.apply {

            val media = MediaItem.fromUri(video.videos.large.url)
            player.setMediaItem(media)
            player.prepare()
            player.play()

            loadProfile(this@VideoDetailActivity, userImg, video.userImageURL)
            userName.text = video.user
            tvViews.text = "${video.views}"
            tvLikes.text = "${video.likes}"
            tvComments.text = "${video.comments}"
        }

    }

    fun downloadAsExo(){
        val request = DownloadRequest.Builder(DOWNLOAD_VIDEO_ID, viewModel.video.videos.large.url.toUri()).build()
        DownloadService.sendAddDownload(this, DownloadMedia::class.java, request, false)
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onStart() {
        super.onStart()
        if (player != null)
            player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}