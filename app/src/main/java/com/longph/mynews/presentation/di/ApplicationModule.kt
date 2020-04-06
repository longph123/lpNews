package com.longph.mynews.presentation.di

import android.content.Context
import com.longph.domain.NewsRepository
import com.longph.movieapp_mvvm.data.RestAPIs
import com.longph.movieapp_mvvm.data.RestClient
import com.longph.movieapp_mvvm.data.RestClientImpl
import com.longph.mynews.MainApplication
import com.longph.mynews.data.database.NewsDb
import com.longph.mynews.data.database.NewsFeedDao
import com.longph.mynews.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MainApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideRestClient(): RestClient {
        return RestClientImpl()
    }

    @Provides
    @Singleton
    fun provideNewsDb(context: Context): NewsDb {
        return NewsDb.create(context)
    }

    @Provides
    @Singleton
    fun provideCustomerDao(db: NewsDb): NewsFeedDao {
        return db.newsFeedDao()
    }

    @Singleton
    @Provides
    fun provideNewsRepository(restClient: RestClient, newsFeedDao: NewsFeedDao): NewsRepository {
        var restApis = restClient.createRestApi(RestAPIs::class.java)
        return NewsRepositoryImpl(restApis, newsFeedDao)
    }
}