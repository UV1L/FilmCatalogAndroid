package com.example.filmcatalog.dagger

import com.example.data.registration.RegisterRepositoryImpl
import com.example.data.registration.RegisterService
import com.example.domain.auth.repo.RegisterRepository
import com.example.domain.auth.use_case.RegisterUseCase
import com.example.domain.auth.use_case.RegisterUseCaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

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
    fun provideRegisterService(@Named("AuthRetrofit") retrofit: Retrofit): RegisterService {
        return retrofit.create(RegisterService::class.java)
    }
}