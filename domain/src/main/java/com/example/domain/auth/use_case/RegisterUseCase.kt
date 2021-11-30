package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Resource
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {

    suspend fun register(email: String, username: String, password_1: String, password_2: String): Flow<Resource<String>>
}