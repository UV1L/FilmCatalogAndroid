package com.example.filmcatalog.dagger

import com.example.data.auth.AuthService
import com.example.data.films.FilmsRepositoryImpl
import com.example.data.films.FilmsService
import com.example.domain.auth.repo.FilmsRepository
import com.example.domain.auth.use_case.FilmsUseCase
import com.example.domain.auth.use_case.FilmsUseCaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class FilmsModule {

    @Provides
    fun provideFilmsRepository(filmsService: FilmsService): FilmsRepository {
        return FilmsRepositoryImpl(filmsService)
    }

    @Provides
    fun provideFilmsService(@Named("FilmsRetrofit") retrofit: Retrofit): FilmsService {
        return retrofit.create(FilmsService::class.java)
    }
}