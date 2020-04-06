package com.longph.mynews.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.longph.domain.Content
import com.longph.domain.Image
import com.longph.domain.Publisher
import java.lang.reflect.Type
import java.util.*


class NewsTypeConverters {

    var gson = Gson()

    @TypeConverter
    fun storeStringToPublisher(data: String?): Publisher? {
        val objectType: Type =
            object : TypeToken<Publisher>() {}.type
        return gson.fromJson<Publisher>(data, objectType)
    }

    @TypeConverter
    fun storeStringToImage(data: String?): Image? {
        val objectType: Type =
            object : TypeToken<Image>() {}.type
        return gson.fromJson<Image>(data, objectType)
    }

    @TypeConverter
    fun storeStringToContent(data: String?): Content? {
        val objectType: Type =
            object : TypeToken<Content>() {}.type
        return gson.fromJson<Content>(data, objectType)
    }

    @TypeConverter
    fun storeStringToImageList(data: String?): List<Image>? {

        if (data == null) {
            return Collections.emptyList()
        }

        val objectType: Type =
            object : TypeToken<List<Image>>() {}.type
        return gson.fromJson<List<Image>>(data, objectType)
    }

    @TypeConverter
    fun storePublisherToString(publisher: Publisher?): String {
        return gson.toJson(publisher)
    }

    @TypeConverter
    fun storeImageToString(image: Image?): String {
        return gson.toJson(image)
    }

    @TypeConverter
    fun storeContentToString(content: Content?): String {
        return gson.toJson(content)
    }

    @TypeConverter
    fun storeImageListToString(images: List<Image>?): String {
        return gson.toJson(images)
    }
}