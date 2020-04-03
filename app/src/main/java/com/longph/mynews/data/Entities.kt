package com.longph.domain

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("document_id") val documentId: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("content_type") val content_type: String,
    @SerializedName("published_date") val published_date: String,
    @SerializedName("publisher") val publisher: Publisher,
    @SerializedName("origin_url") val originUrl: String,
    @SerializedName("avatar") val avatar: Image,
    @SerializedName("images") val images: List<Image>,
    @SerializedName("content") val content: Content
)

data class NewsItems(
    val items: List<News>
)

data class Publisher(
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
    val duration: Int
)