package com.example.filmcatalog.dagger

import com.example.domain.auth.use_case.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AuthModule::class,
    RegistrationModule::class,
    FilmsModule::class,
    UserModule::class,
    AuthRetrofitModule::class,
    FilmsRetrofitModule::class,
    UserRetrofitModule::class
])
interface ApplicationComponent {

    fun injectAuthUseCase(): AuthUseCase

    fun injectRegisterUseCase(): RegisterUseCase

    fun injectFilmsUseCase(): FilmsUseCase

    fun injectAddFavouritesUseCase(): AddFavouritesUseCase

    fun injectGetFavouritesUseCase(): GetFavouritesUseCase
}