package com.elkfrawy.pixaby.presentation.video

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.elkfrawy.pixaby.databinding.VideoViewBinding
import com.elkfrawy.pixaby.domain.model.VideoHit
import com.elkfrawy.pixaby.presentation.utils.getDuration
import com.elkfrawy.pixaby.presentation.utils.loadThumbnail
import com.elkfrawy.pixaby.presentation.utils.show
import com.elkfrawy.pixaby.presentation.utils.videoThumbnail

class VideoAdapter(val context: Context, val listener: SetOnVideoClick): PagingDataAdapter<VideoHit, VideoAdapter.VideoViewHolder>(comparator) {

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)
        videoThumbnail(context, holder.binding.videoThumbnail, video?.videos?.tiny?.url!!)
        holder.binding.thumbnailInfo.show()
        holder.binding.tvVideoDuration.text = getDuration(video.duration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflate = VideoViewBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = VideoViewHolder(inflate)
        holder.binding.root.setOnClickListener {
            listener.onVideoClickListener(getItem(holder.absoluteAdapterPosition)!!)
        }
        return holder
    }

    class VideoViewHolder(val binding: VideoViewBinding): ViewHolder(binding.root){}

    companion object{

        val comparator = object : DiffUtil.ItemCallback<VideoHit>(){
            override fun areItemsTheSame(oldItem: VideoHit, newItem: VideoHit): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: VideoHit, newItem: VideoHit): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface SetOnVideoClick{
        fun onVideoClickListener(video: VideoHit)
    }

}