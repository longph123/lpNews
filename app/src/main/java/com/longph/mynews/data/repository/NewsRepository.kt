package com.longph.domain

import com.longph.mynews.data.remote.ApiResponse

interface NewsRepository {
    suspend fun getNewsList() : ApiResponse<List<News>>

    suspend fun getNewDetail(
        newsId: String
    ) : ApiResponse<News>
}