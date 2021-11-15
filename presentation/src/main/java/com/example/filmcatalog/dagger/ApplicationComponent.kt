package com.example.filmcatalog.dagger

import com.example.domain.auth.use_case.AuthUseCase
import com.example.filmcatalog.dagger.AuthModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthModule::class])
interface ApplicationComponent {

    fun injectAuthUseCase(): AuthUseCase
}