package com.longph.mynews.presentation.di

import com.longph.domain.NewsRepository
import com.longph.movieapp_mvvm.data.RestAPIs
import com.longph.movieapp_mvvm.data.RestClient
import com.longph.movieapp_mvvm.data.RestClientImpl
import com.longph.mynews.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideRestClient(): RestClient {
        return RestClientImpl()
    }

    @Singleton
    @Provides
    fun provideNewsRepository(): NewsRepository {
        var restApis = provideRestClient().createRestApi(RestAPIs::class.java)
        return NewsRepositoryImpl(restApis)
    }
}