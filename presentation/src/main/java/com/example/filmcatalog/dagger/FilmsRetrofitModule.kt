package com.example.filmcatalog.dagger

import com.example.filmcatalog.utils.Const
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class FilmsRetrofitModule {

    @Named("FilmsRetrofit")
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL_FILMS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}