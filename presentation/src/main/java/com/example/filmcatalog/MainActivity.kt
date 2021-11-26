package com.example.filmcatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.domain.auth.entities.User

class MainActivity : AppCompatActivity(), AuthObserver {

    var currentSessionUser: User? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_FilmCatalog)
        setContentView(R.layout.activity_main)
    }

    override fun setCurrentSessionUser(user: User) {
        currentSessionUser = user
    }
}

interface AuthObserver {

    fun setCurrentSessionUser(user: User)
}