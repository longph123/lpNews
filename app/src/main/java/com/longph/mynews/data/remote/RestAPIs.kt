package com.longph.movieapp_mvvm.data

import com.longph.domain.News
import com.longph.domain.NewsItems
import retrofit2.Response
import retrofit2.http.GET

interface RestAPIs {

    @GET("newsfeed.json")
    suspend fun getNewsList(): Response<NewsItems>

    @GET("detail.json")
    suspend fun getNewsDetail(): Response<News>
}