package com.longph.movieapp_mvvm.data

import com.longph.domain.News
import com.longph.mynews.data.remote.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestAPIs {

    @GET("newsfeed.json")
    fun getNewsList() : Deferred<Response<List<News>>>

    @GET("detail.json")
    fun getNewsDetail() : Deferred<Response<News>>
}