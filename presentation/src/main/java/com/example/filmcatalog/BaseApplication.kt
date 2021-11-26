package com.example.filmcatalog

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.filmcatalog.dagger.ApplicationComponent
import com.example.filmcatalog.dagger.DaggerApplicationComponent

class BaseApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
    }

    fun saveTokenToSharedPreferences(token: String) {
        val sp = getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("token", token).apply()
    }

    fun getToken(): String? {
        val sp = getSharedPreferences("tokenPrefs",Context.MODE_PRIVATE)

        return sp.getString("token", "")
    }
}