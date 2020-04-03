package com.longph.mynews.presentation.adapters.viewholders.newsfeed

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.longph.domain.News
import com.longph.mynews.R
import com.longph.mynews.presentation.widgets.CustomVideoView
import kotlinx.android.synthetic.main.item_news.view.*
import tcking.github.com.giraffeplayer2.VideoInfo
import tcking.github.com.giraffeplayer2.VideoView

class NewsVideoHolder(
    dataBinding: ViewDataBinding
) : NewsFeedHolder(dataBinding){

    override fun setupData(position: Int, news: News){
        super.setupData(position, news)
        var deviceWidth = itemView.context.resources.displayMetrics.widthPixels
        var aspectRadio = news.content.previewImage.height.toFloat() / news.content.previewImage.width
        var videoPreviewHeight = calculatedHeight(deviceWidth, aspectRadio)
        var videoPreview = ImageView(itemView.context)
        var playButton = ImageView(itemView.context)


        var layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        playButton.layoutParams = layoutParams
        playButton.setImageResource(R.drawable.ic_play_button)

        videoPreview.layoutParams = FrameLayout.LayoutParams(deviceWidth, videoPreviewHeight)
        Glide.with(itemView.context)
            .load(news.content.previewImage.href)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .dontAnimate()
            .into(videoPreview)
        itemView.flContent.addView(videoPreview)
        itemView.flContent.addView(playButton)
    }

    private fun calculatedHeight(width: Int, aspectRadio: Float) = (width * aspectRadio).toInt()
}