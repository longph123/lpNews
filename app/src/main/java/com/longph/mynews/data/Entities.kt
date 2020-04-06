package com.longph.domain

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.longph.mynews.data.database.NewsTypeConverters

@Entity(tableName = "news")
@TypeConverters(NewsTypeConverters::class)
data class News(
    @PrimaryKey @SerializedName("document_id") var documentId: String,
    @SerializedName("title") var title: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("content_type") var content_type: String?,
    @SerializedName("published_date") var published_date: String?,
    @SerializedName("publisher") var publisher: Publisher?,
    @SerializedName("origin_url") var originUrl: String?,
    @SerializedName("avatar") var avatar: Image?,
    @SerializedName("images") var images: List<Image>?,
    @SerializedName("content") var content: Content?,
    @Ignore @SerializedName("sections") val sections: List<Section>
) {
    constructor() : this(
        "", null, null, null,
        null, null, null, null, listOf(), null, listOf()
    )
}

data class Section(
    @SerializedName("section_type") val sectionType: Int,
    var title: String?,
    var description: String?,
    var content: Content?
)

data class NewsItems(
    val items: List<News>
)

data class Publisher(
    @PrimaryKey
    val id: String,
    val name: String,
    val icon: String
)

data class Image(
    @SerializedName("main_color") val mainColor: String,
    val href: String,
    val width: Int,
    val height: Int
)

data class Content(
    @SerializedName("preview_image") val previewImage: Image,
    val href: String,
    val duration: Int,
    val text: String,
    val markups: List<Markup>,
    val caption: String
)

data class Markup(
    @SerializedName("markup_type") val markupType: Int,
    val start: Int,
    val end: Int,
    val href: String
)

