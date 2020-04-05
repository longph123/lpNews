package com.longph.mynews.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.longph.domain.News
import com.longph.mynews.databinding.ItemNewsBinding
import com.longph.mynews.presentation.adapters.viewholders.newsfeed.NewsFeedHolder
import com.longph.mynews.presentation.adapters.viewholders.newsfeed.NewsGalleriesHolder
import com.longph.mynews.presentation.adapters.viewholders.newsfeed.NewsVideoHolder

class NewsFeedAdapter : RecyclerView.Adapter<NewsFeedHolder>() {

    enum class FeedType {
        OVERVIEW, VIDEO, GALLERY
    }

    private var newsList: MutableList<News> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedHolder {
        var inflater = LayoutInflater.from(parent.context)
        var itemNewsBinding = ItemNewsBinding.inflate(inflater, parent, false)
        return when (viewType) {
            FeedType.VIDEO.ordinal -> NewsVideoHolder(itemNewsBinding)
            FeedType.GALLERY.ordinal -> NewsGalleriesHolder(itemNewsBinding)
            else -> NewsFeedHolder(itemNewsBinding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var news = newsList[position]
        var feedType = when (news.content_type) {
            "video" -> FeedType.VIDEO.ordinal
            "story", "article", "gallery", "long_form" -> FeedType.GALLERY.ordinal
            else -> FeedType.OVERVIEW.ordinal
        }

        return feedType
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsFeedHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.setupData(position, newsList[position])
    }

    fun updateList(newsList: List<News>) {
        this.newsList.clear()
        this.newsList.addAll(newsList)
        notifyDataSetChanged()
    }
}