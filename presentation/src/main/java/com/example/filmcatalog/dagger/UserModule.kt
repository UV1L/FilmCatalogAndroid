package com.example.filmcatalog.dagger

import com.example.data.user.UserRepositoryImpl
import com.example.data.user.UserService
import com.example.domain.auth.repo.UserRepository
import com.example.domain.auth.use_case.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class UserModule {

    @Provides
    fun provideAddFavouritesUseCase(userRepository: UserRepository): FavouritesUseCase.AddFavouritesUseCase {

        return AddFavouritesUseCaseImpl(userRepository)
    }

    @Provides
    fun provideGetFavouritesUseCase(userRepository: UserRepository): FavouritesUseCase.GetFavouritesUseCase {

        return GetFavouritesUseCaseImpl(userRepository)
    }

    @Provides
    fun provideUserRepository(userService: UserService): UserRepository {

        return UserRepositoryImpl(userService)
    }

    @Provides
    fun provideUserService(@Named("UserRetrofit") retrofit: Retrofit): UserService {

        return retrofit.create(UserService::class.java)
    }
}