package com.elkfrawy.pixaby.presentation.photo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.util.UnstableApi
import com.elkfrawy.pixaby.R
import com.elkfrawy.pixaby.databinding.FragmentPhotoBinding
import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.presentation.utils.PHOTO_DETAIL_KEY
import com.elkfrawy.pixaby.presentation.utils.hide
import com.elkfrawy.pixaby.presentation.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@UnstableApi @AndroidEntryPoint
class PhotoFragment : Fragment(), PhotoAdapter.SetOnPhotoClick {

    lateinit var binding: FragmentPhotoBinding
    lateinit var photoAdapter: PhotoAdapter
    val viewModel: PhotoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        photoAdapter = PhotoAdapter(requireContext(), this)
        binding.photoRv.apply {
            adapter = photoAdapter
            setHasFixedSize(true)
        }
        binding.edPhotoLayout.setEndIconOnClickListener {
            binding.photoPb.show()
            binding.searchImg.hide()

            val text = binding.edPhotoSearch.text.toString()
            lifecycleScope.launch {
                viewModel.getPhotos(text).collectLatest {
                    binding.photoPb.hide()
                    binding.photoRv.show()
                    photoAdapter.submitData(it)
                }
            }
        }

    }

    override fun onPhotoClickListener(photo: Photo) {
        val intent = Intent(requireContext(), PhotoDetailsActivity::class.java)
        intent.putExtra(PHOTO_DETAIL_KEY, photo)
        startActivity(intent)
    }
}