package com.elkfrawy.pixaby.presentation.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.elkfrawy.pixaby.R

fun loadImage(context: Context, imageView: ImageView, url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.loading_gif)
        .thumbnail(Glide.with(context).load(R.drawable.loading_gif))
        .error(R.drawable.no_image)
        .priority(Priority.HIGH)
        .into(imageView)
}

fun videoThumbnail(context: Context, imageView: ImageView, url: String) {
    val rb = Glide.with(context).asDrawable().sizeMultiplier(0.6f)

    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.loading_gif)
        .thumbnail(rb)
        .thumbnail(Glide.with(context).load(R.drawable.loading_gif))
        .error(R.drawable.no_image)
        .into(imageView)
}

fun loadProfile(context: Context, imageView: ImageView, url: String?){
    Glide.with(context)
        .load(url)
        .fallback(R.drawable.user)
        .placeholder(R.drawable.loading_gif)
        .thumbnail(Glide.with(context).load(R.drawable.loading_gif))
        .error(R.drawable.no_image)
        .into(imageView)
}

fun loadThumbnail(context: Context, imageView: ImageView, url: String) {

    val rb = Glide.with(context).asDrawable().sizeMultiplier(0.5f)

    Glide.with(context)
        .load(url)
        .thumbnail(rb)
        .thumbnail(Glide.with(context).load(R.drawable.loading_gif))
        .placeholder(R.drawable.loading_gif)
        .error(R.drawable.no_image)
        .into(imageView)
}

fun glideClear(context: Context, imageView: ImageView){
    Glide.with(context).clear(imageView)
}

fun getDuration(sec: Int): String {
    val h = sec / 3600;
    val m = (sec % 3600) / 60;
    val s = sec % 60;

    return String.format("%02d:%02d", m, s)
}


fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}