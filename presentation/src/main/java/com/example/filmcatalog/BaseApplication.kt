package com.example.filmcatalog

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.domain.auth.entities.User
import com.example.filmcatalog.dagger.ApplicationComponent
import com.example.filmcatalog.dagger.DaggerApplicationComponent

class BaseApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
    }

    fun saveCurrentUsername(username: String) {
        val sp = applicationContext.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("username", username).apply()
    }

    fun saveCurrentUserId(id: Int) {
        val sp = applicationContext.getSharedPreferences("userIdPrefs", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putInt("userid", id).apply()
    }

    fun saveTokenToSharedPreferences(token: String) {
        val sp = applicationContext.getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("token", token).apply()
    }

    fun getToken(): String? {
        val sp = applicationContext.getSharedPreferences("tokenPrefs", Context.MODE_PRIVATE)

        return sp.getString("token", "")
    }

    fun getCurrentUsername(): String? {
        val sp = applicationContext.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)

        return sp.getString("username", "")
    }

    fun getCurrentUserId(): Int {
        val sp = applicationContext.getSharedPreferences("userIdPrefs", Context.MODE_PRIVATE)

        return sp.getInt("userid", Int.MIN_VALUE)
    }
}