package com.example.filmcatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmcatalog.fragments.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_FilmCatalog)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, LoginFragment::class.java, null)
                .commitNow()
        }
    }
}