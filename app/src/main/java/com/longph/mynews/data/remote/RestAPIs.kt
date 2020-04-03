package com.longph.movieapp_mvvm.data

import com.longph.domain.News
import com.longph.domain.NewsItems
import com.longph.mynews.data.remote.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestAPIs {

    @GET("newsfeed.json")
    suspend fun getNewsList() : Response<NewsItems>

    @GET("detail.json")
    suspend fun getNewsDetail() : Response<News>
}