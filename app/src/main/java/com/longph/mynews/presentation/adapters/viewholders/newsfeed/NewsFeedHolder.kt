package com.longph.mynews.presentation.adapters.viewholders.newsfeed

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.longph.domain.News
import com.longph.mynews.BR
import com.longph.mynews.R
import com.longph.mynews.presentation.adapters.viewholders.AbstractNewsHolder

open class NewsFeedHolder(
        private val dataBinding: ViewDataBinding
    ) : AbstractNewsHolder<News>(dataBinding.root) {

    override fun setupData(position: Int, news: News){
        dataBinding.apply {
            setVariable(BR.newsItem, news)
            executePendingBindings()
        }

        itemView.setOnClickListener {
            var arguments: Bundle = bundleOf("newsId" to news.documentId)
            itemView.findNavController().navigate(R.id.action_homeFragment_to_feedDetailFragment, arguments)
        }
    }
}