package com.example.domain.auth.use_case

import com.example.domain.auth.repo.AuthRepository
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    suspend fun auth(username: String, password: String): Flow<String>
}