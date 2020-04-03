package com.longph.mynews.presentation.adapters.viewholders.newsfeed

import android.text.format.DateUtils
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.longph.domain.News
import com.longph.mynews.BR

open class NewsFeedHolder(
        var dataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {
    open fun setupData(position: Int, news: News){
        dataBinding.apply {
            setVariable(BR.newsItem, news)
            executePendingBindings()
        }
    }
}