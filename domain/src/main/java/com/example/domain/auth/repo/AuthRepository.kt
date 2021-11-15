package com.example.domain.auth.repo

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun auth(username: String, password: String): Flow<String>
}