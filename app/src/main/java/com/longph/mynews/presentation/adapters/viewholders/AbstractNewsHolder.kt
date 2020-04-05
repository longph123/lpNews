package com.longph.mynews.presentation.adapters.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.longph.mynews.R
import kotlinx.android.synthetic.main.item_section_image.view.*

abstract class AbstractNewsHolder<T: Any>(
    itemView: View
) : RecyclerView.ViewHolder(itemView){

    abstract fun setupData(position: Int, item: T)

    fun setupImageLoader(context: Context, imageUrl: String, targetImageView: ImageView){
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .dontAnimate()
            .into(targetImageView)
    }
}