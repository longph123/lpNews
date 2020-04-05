package com.longph.mynews.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.longph.domain.Section
import com.longph.mynews.databinding.ItemSectionImageBinding
import com.longph.mynews.databinding.ItemSectionTextBinding
import com.longph.mynews.databinding.ItemSectionVideoBinding
import com.longph.mynews.presentation.adapters.viewholders.newsdetail.NewsDetailHolder

class NewsDetailAdapter : RecyclerView.Adapter<NewsDetailHolder>() {

    enum class SectionType {
        NONE, TEXT, VIDEO, IMAGE
    }

    private var sections: MutableList<Section> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsDetailHolder {
        var inflater = LayoutInflater.from(parent.context)
        var dataBinding = when (viewType) {
            SectionType.VIDEO.ordinal -> ItemSectionVideoBinding.inflate(inflater, parent, false)
            SectionType.IMAGE.ordinal -> ItemSectionImageBinding.inflate(inflater, parent, false)
            else -> ItemSectionTextBinding.inflate(inflater, parent, false)
        }

        return NewsDetailHolder(dataBinding)
    }

    override fun getItemViewType(position: Int): Int {
        return sections[position].sectionType
    }

    override fun getItemCount(): Int = sections.size

    override fun onBindViewHolder(holder: NewsDetailHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.setupData(position, sections[position])
    }

    fun updateList(title: String, description: String, sections: List<Section>) {
        this.sections.clear()
        this.sections.add(Section(SectionType.NONE.ordinal, title, null, null))
        this.sections.add(Section(SectionType.NONE.ordinal, null, description, null))
        this.sections.addAll(sections)
        notifyDataSetChanged()
    }
}