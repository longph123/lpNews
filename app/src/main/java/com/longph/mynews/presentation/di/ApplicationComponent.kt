package com.longph.mynews.presentation.di

import com.longph.mynews.presentation.fragments.FeedDetailFragment
import com.longph.mynews.presentation.fragments.NewsFeedFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(newsFeedFragment: NewsFeedFragment)
    fun inject(newsDetailFragment: FeedDetailFragment)
}