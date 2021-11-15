package com.example.data.auth

import com.example.domain.auth.repo.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {

    override suspend fun auth(username: String, password: String): Flow<String> {

        return flow {
            emit(authService.auth(AuthRequest(username, password)).token)
        }
    }
}