package com.longph.mynews.presentation.adapters.viewholders.newsdetail

import android.graphics.Color
import android.net.Uri
import android.widget.LinearLayout
import androidx.core.widget.TextViewCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.textview.MaterialTextView
import com.longph.domain.Section
import com.longph.mynews.BR
import com.longph.mynews.R
import com.longph.mynews.databinding.ItemSectionImageBinding
import com.longph.mynews.databinding.ItemSectionTextBinding
import com.longph.mynews.databinding.ItemSectionVideoBinding
import com.longph.mynews.presentation.adapters.NewsDetailAdapter
import com.longph.mynews.presentation.adapters.viewholders.AbstractNewsHolder
import kotlinx.android.synthetic.main.item_section_image.view.*
import kotlinx.android.synthetic.main.item_section_text.view.*
import kotlinx.android.synthetic.main.item_section_video.view.*

class NewsDetailHolder(
    private val dataBinding: ViewDataBinding
) : AbstractNewsHolder<Section>(dataBinding.root) {
    override fun setupData(position: Int, section: Section){
        dataBinding.apply {
            setVariable(BR.sectionItem, section)
            executePendingBindings()
            setupSectionByBindingType(this, section)
        }
    }

    private fun setupSectionByBindingType(dataBinding: ViewDataBinding, section: Section){
        when (dataBinding){
            is ItemSectionVideoBinding -> {
                section.content?.apply {
                    itemView.vvSectionVideo.videoInfo.bgColor = Color.GRAY
                    itemView.vvSectionVideo.setVideoPath(this.href).player.start()
                }
            }

            is ItemSectionImageBinding -> {
                section.content?.apply {
                    setupImageLoader(itemView.context, this.href, itemView.ivSectionImage)
                }
            }

            is ItemSectionTextBinding -> {
                section.title?.apply {
                    TextViewCompat.setTextAppearance(itemView.tvText, R.style.TitleTextStyle)
                    itemView.tvText.text = this
                }

                section.description?.apply {
                    TextViewCompat.setTextAppearance(itemView.tvText, R.style.DescriptionTextStyle)
                    itemView.tvText.text = this
                }
            }
        }
    }
}