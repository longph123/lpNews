package com.longph.mynews.presentation.adapters.viewholders.newsfeed

import android.content.Context
import android.util.Size
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.longph.domain.Image
import com.longph.domain.News
import com.longph.mynews.R
import kotlinx.android.synthetic.main.item_news.view.*

class NewsGalleriesHolder (
    dataBinding: ViewDataBinding
) : NewsFeedHolder(dataBinding){

    override fun setupData(position: Int, news: News) {
        super.setupData(position, news)

        news.images?.apply {
            var recyclerView = RecyclerView(itemView.context)
            var layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)

            recyclerView.layoutParams = layoutParams
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            recyclerView.adapter = ImageAdapter(news.images)
            itemView.flContent.addView(recyclerView)
        }
    }

    class ImageAdapter(var imageList: List<Image>) : RecyclerView.Adapter<ImageHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
            var scaledImageWidth = calculateImageWidth(parent.context, imageList.size)
            var scaledImageHeight = calculateImageHeight(scaledImageWidth)
            var imageView = ImageView(parent.context)
            imageView.layoutParams = ViewGroup.LayoutParams(scaledImageWidth, scaledImageHeight)
            return ImageHolder(imageView)
        }

        override fun getItemCount(): Int = imageList.size?: 0

        override fun onBindViewHolder(holder: ImageHolder, position: Int) {
            holder.setImageUrl(imageList[position].href)
        }

        fun calculateImageWidth(context: Context, imagesListSize: Int) : Int{
            var deviceWidth = context.resources.displayMetrics.widthPixels
            if(imagesListSize >= 3){
                return deviceWidth / 3
            }
            return deviceWidth / imagesListSize
        }

        fun calculateImageHeight(imageWidth: Int) : Int{
            return (imageWidth * (3f / 4)).toInt()
        }
    }

    class ImageHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView){

        fun setImageUrl(imageUrl: String){

            Glide.with(itemView.context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .dontAnimate()
                .into(imageView)
        }
    }
}