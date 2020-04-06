package com.longph.mynews

import android.app.Application
import com.longph.mynews.presentation.di.ApplicationComponent
import com.longph.mynews.presentation.di.ApplicationModule
import com.longph.mynews.presentation.di.DaggerApplicationComponent

class MainApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}