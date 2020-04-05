package com.longph.mynews.presentation.adapters.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.longph.mynews.R

abstract class AbstractNewsHolder<T : Any>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    abstract fun setupData(position: Int, item: T)

    fun setupImageLoader(context: Context, imageUrl: String, targetImageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .dontAnimate()
            .into(targetImageView)
    }
}