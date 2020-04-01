package com.longph.domain

import com.longph.mynews.data.remote.ApiResponse
import com.longph.mynews.data.repository.NewsRepositoryImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsUseCase @Inject constructor(val newsRepository: NewsRepository){
    suspend fun getNewsList() : ApiResponse<List<News>>{
        return newsRepository.getNewsList()
    }

    suspend fun getNewsDetail(newsId: String) : ApiResponse<News> {
        return newsRepository.getNewDetail(newsId)
    }
}