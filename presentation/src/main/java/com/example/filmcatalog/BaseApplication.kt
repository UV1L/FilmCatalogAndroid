package com.example.filmcatalog

import android.app.Application
import com.example.filmcatalog.dagger.ApplicationComponent
import com.example.filmcatalog.dagger.DaggerApplicationComponent

class BaseApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
    }
}