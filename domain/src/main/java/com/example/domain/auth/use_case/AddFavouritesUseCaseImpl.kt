package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Resource
import com.example.domain.auth.repo.UserRepository
import kotlinx.coroutines.flow.Flow

class AddFavouritesUseCaseImpl(private val userRepository: UserRepository) : AddFavouritesUseCase {

    override suspend fun addFavourite(token: String, filmId: Int): Flow<Resource<Nothing>> {

        return userRepository.addFavourite(token, filmId)
    }
}