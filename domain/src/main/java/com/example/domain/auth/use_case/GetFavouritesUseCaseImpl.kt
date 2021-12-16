package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.repo.UserRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesUseCaseImpl(private val userRepository: UserRepository) : FavouritesUseCase.GetFavouritesUseCase {

    override suspend fun getFavourites(token: String): Flow<Resource<Set<Film>>> {

        return userRepository.getFavourites(token)
    }
}