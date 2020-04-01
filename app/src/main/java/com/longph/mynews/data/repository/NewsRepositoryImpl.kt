package com.longph.mynews.data.repository

import androidx.annotation.VisibleForTesting
import com.longph.domain.News
import com.longph.domain.NewsRepository
import com.longph.movieapp_mvvm.data.RestAPIs
import com.longph.movieapp_mvvm.data.RestClient
import com.longph.mynews.data.remote.ApiResponse
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

class NewsRepositoryImpl constructor(
    val restAPIs: RestAPIs
) : NewsRepository{

    override suspend fun getNewsList(): ApiResponse<List<News>> {
        return safeApiCall(
            call = {
                var result = restAPIs.getNewsList().await()
                handleResult(result)
            },
            errorMessage = "IO Exception"
        )
    }

    override suspend fun getNewDetail(newsId: String): ApiResponse<News> {
        return safeApiCall(
            call = {
                var result = restAPIs.getNewsDetail().await()
                handleResult(result)
            },
            errorMessage = "IO Exception"
        )
    }

    private fun <T: Any> handleResult(result: Response<T>) : ApiResponse<T>{
        if(result.isSuccessful)
            return ApiResponse.Success(result.body())
        return ApiResponse.Error(result.code(), result.message())
    }

    suspend fun <T: Any> safeApiCall(
        call: suspend () -> ApiResponse<T>,
        errorMessage: String
    ): ApiResponse<T>{
        return try {
            call()
        }catch (e: Exception){
            ApiResponse.Exception(IOException(errorMessage, e))
        }
    }
}