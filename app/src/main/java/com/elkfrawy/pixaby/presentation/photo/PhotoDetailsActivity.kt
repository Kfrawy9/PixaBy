package com.elkfrawy.pixaby.presentation.photo

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.offline.DownloadRequest
import androidx.media3.exoplayer.offline.DownloadService
import com.elkfrawy.pixaby.R
import com.elkfrawy.pixaby.databinding.ActivityPhotoDetailsBinding
import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.domain.service.DownloadMedia
import com.elkfrawy.pixaby.domain.service.PixaDownload
import com.elkfrawy.pixaby.presentation.utils.DOWNLOAD_PHOTO_ID
import com.elkfrawy.pixaby.presentation.utils.PHOTO_DETAIL_KEY
import com.elkfrawy.pixaby.presentation.utils.hide
import com.elkfrawy.pixaby.presentation.utils.loadImage
import com.elkfrawy.pixaby.presentation.utils.loadProfile
import com.elkfrawy.pixaby.presentation.utils.permissionGranted
import com.elkfrawy.pixaby.presentation.utils.show
import dagger.hilt.android.AndroidEntryPoint
import java.security.Permission

@UnstableApi
@AndroidEntryPoint
class PhotoDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityPhotoDetailsBinding
    val viewModel: PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val intent = intent
        intent.extras?.let {
            val photo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(PHOTO_DETAIL_KEY, Photo::class.java)
            } else {
                it.getParcelable(PHOTO_DETAIL_KEY)
            }
            photo?.let {
                viewModel.photo = it
                putData(viewModel.photo)
            }
        }

        binding.downloadImg.setOnClickListener {
           downloadFile()
        }
    }

    fun putData(photo: Photo) {
        binding.apply {

            loadImage(this@PhotoDetailsActivity, binding.detailImg, photo.largeImageURL)
            println("Farrag: ${photo.userImageURL}")
            loadProfile(
                this@PhotoDetailsActivity,
                binding.userImg,
                if (photo.userImageURL.isBlank()) null else photo.userImageURL
            )


            userName.text = photo.user
            tvViews.text = "${photo.views}"
            tvLikes.text = "${photo.likes}"
            tvComments.text = "${photo.comments}"
        }


    }

    fun downloadFile() {
        PixaDownload(this).downloadFile(viewModel.photo.largeImageURL)
    }


}