package com.longph.mynews.data.repository

import com.longph.domain.News
import com.longph.domain.NewsItems
import com.longph.domain.NewsRepository
import com.longph.movieapp_mvvm.data.RestAPIs
import com.longph.mynews.data.database.NewsFeedDao
import com.longph.mynews.data.remote.ApiResponse
import java.io.IOException

class NewsRepositoryImpl constructor(
    private val restAPIs: RestAPIs,
    private val newsFeedDao: NewsFeedDao
) : NewsRepository {

    override suspend fun getNewsList(): ApiResponse<NewsItems> {
        return safeApiCall(
            call = {
                var result = restAPIs.getNewsList()
                if (result.isSuccessful) {
                    if (result.body() is NewsItems)
                        cacheNews((result.body() as NewsItems).items)

                    ApiResponse.Success(result.body())
                } else
                    ApiResponse.Success(NewsItems(newsFeedDao.getNewsList()))
            },
            errorMessage = "Recheck your network connection and try again",
            loadFromCache = ApiResponse.Success(NewsItems(newsFeedDao.getNewsList()))
        )
    }

    override suspend fun getNewDetail(newsId: String): ApiResponse<News> {
        return safeApiCall(
            call = {
                var result = restAPIs.getNewsDetail()
                if (result.isSuccessful) {
                    ApiResponse.Success(result.body())
                } else
                    ApiResponse.Error(result.code(), result.message())
            },
            errorMessage = "Recheck your network connection and try again",
            loadFromCache = null
        )
    }

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> ApiResponse<T>,
        errorMessage: String,
        loadFromCache: ApiResponse<T>?
    ): ApiResponse<T> {
        return try {
            call()
        } catch (e: Exception) {
            loadFromCache?.apply {
                return loadFromCache
            }
            return ApiResponse.Exception(IOException(errorMessage, e))
        }
    }

    private fun cacheNews(customers: List<News>?) {
        if (customers != null) {
            newsFeedDao.deleteAllNews()
            newsFeedDao.insertAllNews(customers)
        }
    }
}