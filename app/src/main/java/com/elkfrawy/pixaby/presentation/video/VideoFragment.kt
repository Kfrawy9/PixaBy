package com.elkfrawy.pixaby.presentation.video

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.elkfrawy.pixaby.R
import com.elkfrawy.pixaby.databinding.FragmentVideoBinding
import com.elkfrawy.pixaby.databinding.VideoViewBinding
import com.elkfrawy.pixaby.domain.model.VideoHit
import com.elkfrawy.pixaby.presentation.utils.VIDEO_DETAIL_KEY
import com.elkfrawy.pixaby.presentation.utils.glideClear
import com.elkfrawy.pixaby.presentation.utils.hide
import com.elkfrawy.pixaby.presentation.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideoFragment : Fragment(), VideoAdapter.SetOnVideoClick {

    lateinit var binding: FragmentVideoBinding
    val viewModel: VideoViewModel by viewModels()
    lateinit var videoAdapter: VideoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        videoAdapter = VideoAdapter(requireContext(), this)
        binding.videoRv.apply {
            adapter = videoAdapter
            setHasFixedSize(true)
        }
        binding.edVideoLayout.setEndIconOnClickListener {
            binding.videoPb.show()
            binding.searchImg.hide()
            val text = binding.edVideoSearch.text.toString()

            lifecycleScope.launch {
                viewModel.getVideo(text).collectLatest {
                    binding.videoPb.hide()
                    binding.videoRv.show()
                    videoAdapter.submitData(it)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        val bind = VideoViewBinding.inflate(layoutInflater)
        glideClear(requireContext(), bind.videoThumbnail)
    }

    override fun onVideoClickListener(video: VideoHit) {
        val intent = Intent(requireContext(), VideoDetailActivity::class.java)
        intent.putExtra(VIDEO_DETAIL_KEY, video)
        startActivity(intent)
    }

}