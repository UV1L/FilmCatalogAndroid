package com.example.filmcatalog.dagger

import com.example.data.auth.AuthRepositoryImpl
import com.example.data.auth.AuthService
import com.example.domain.auth.repo.AuthRepository
import com.example.domain.auth.use_case.AuthUseCase
import com.example.domain.auth.use_case.AuthUseCaseImpl
import com.example.filmcatalog.utils.Const.BASE_URL_AUTH
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AuthModule {

    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCaseImpl(authRepository)
    }

    @Provides
    fun provideAuthRepository(authService: AuthService): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_AUTH)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}