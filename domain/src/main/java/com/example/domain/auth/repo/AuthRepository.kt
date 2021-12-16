package com.example.domain.auth.repo

import com.example.domain.auth.entities.Resource
import com.example.domain.auth.entities.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun auth(username: String, password: String): Flow<Resource<User>>
}