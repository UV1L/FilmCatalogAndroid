package com.example.filmcatalog.dagger

import com.example.domain.auth.use_case.*
import com.example.filmcatalog.ui.fragments.main_page.FilmListFragment
import com.example.filmcatalog.ui.fragments.main_page.recycler_view.FilmListRecyclerAdapter
import com.example.filmcatalog.ui.fragments.reviews.ReviewsFragment
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

    fun inject(filmListFragment: FilmListFragment)

    fun inject(reviewsFragment: ReviewsFragment)
}