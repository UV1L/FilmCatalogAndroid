package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Resource
import com.example.domain.auth.entities.User
import com.example.domain.auth.repo.AuthRepository
import com.example.domain.auth.use_case.AuthUseCase
import kotlinx.coroutines.flow.Flow

class AuthUseCaseImpl(private val authRepository: AuthRepository) : AuthUseCase {

    override suspend fun auth(username: String, password: String): Flow<Resource<User>> {

        return authRepository.auth(username, password)
    }
}