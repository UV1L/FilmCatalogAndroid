package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Resource
import com.example.domain.auth.entities.User
import com.example.domain.auth.repo.AuthRepository
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    suspend fun auth(username: String, password: String): Flow<Resource<User>>
}