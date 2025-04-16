package com.elkfrawy.pixaby.presentation.photo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elkfrawy.pixaby.databinding.PhotoViewBinding
import com.elkfrawy.pixaby.domain.model.Photo
import com.elkfrawy.pixaby.presentation.utils.loadImage
import com.elkfrawy.pixaby.presentation.utils.loadThumbnail

class PhotoAdapter(val context: Context, val listener: SetOnPhotoClick) :
    PagingDataAdapter<Photo, PhotoAdapter.PhotoViewHolder>(
        comparator
    ) {


    class PhotoViewHolder(val binding: PhotoViewBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotoViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = PhotoViewHolder(binding)
        holder.binding.photoThumbnail.setOnClickListener {
            listener.onPhotoClickListener(getItem(holder.absoluteAdapterPosition)!!)
        }
        return holder
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        val photo = getItem(position)
        loadThumbnail(context, holder.binding.photoThumbnail, photo!!.webformatURL)
    }


    interface SetOnPhotoClick {
        fun onPhotoClickListener(photo: Photo)
    }

    companion object {
        val comparator = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }


}