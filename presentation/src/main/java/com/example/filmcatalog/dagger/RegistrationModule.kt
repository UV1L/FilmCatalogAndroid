package com.example.filmcatalog.dagger

import com.example.data.auth.AuthRepositoryImpl
import com.example.data.auth.AuthService
import com.example.data.auth.RegisterRepositoryImpl
import com.example.data.auth.RegisterService
import com.example.domain.auth.repo.AuthRepository
import com.example.domain.auth.repo.RegisterRepository
import com.example.domain.auth.use_case.AuthUseCase
import com.example.domain.auth.use_case.AuthUseCaseImpl
import com.example.domain.auth.use_case.RegisterUseCase
import com.example.domain.auth.use_case.RegisterUseCaseImpl
import com.example.filmcatalog.utils.Const
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RegistrationModule {

    @Provides
    fun provideRegisterUseCase(registerRepository: RegisterRepository): RegisterUseCase {
        return RegisterUseCaseImpl(registerRepository)
    }

    @Provides
    fun provideRegisterRepository(registerService: RegisterService): RegisterRepository {
        return RegisterRepositoryImpl(registerService)
    }

    @Provides
    fun provideRegisterService(retrofit: Retrofit): RegisterService {
        return retrofit.create(RegisterService::class.java)
    }
}