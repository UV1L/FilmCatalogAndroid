package com.example.filmcatalog.dagger

import com.example.domain.auth.use_case.AuthUseCase
import com.example.domain.auth.use_case.FilmsUseCase
import com.example.domain.auth.use_case.RegisterUseCase
import com.example.filmcatalog.dagger.AuthModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthModule::class, RegistrationModule::class, FilmsModule::class, AuthRetrofitModule::class, FilmsRetrofitModule::class])
interface ApplicationComponent {

    fun injectAuthUseCase(): AuthUseCase

    fun injectRegisterUseCase(): RegisterUseCase

    fun injectFilmsUseCase(): FilmsUseCase
}