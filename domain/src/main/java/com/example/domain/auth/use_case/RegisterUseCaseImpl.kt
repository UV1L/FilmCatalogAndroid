package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Resource
import com.example.domain.auth.repo.RegisterRepository
import kotlinx.coroutines.flow.Flow

class RegisterUseCaseImpl(private val registerRepository: RegisterRepository) : RegisterUseCase {

    override suspend fun register(
        email: String,
        username: String,
        password_1: String,
        password_2: String
    ): Flow<Resource<String>> {

        return registerRepository.register(email, username, password_1, password_2)
    }
}