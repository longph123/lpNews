package com.longph.domain

import com.longph.mynews.data.remote.ApiResponse

interface NewsRepository {
    suspend fun getNewsList() : ApiResponse<NewsItems>

    suspend fun getNewDetail(
        newsId: String
    ) : ApiResponse<News>
}