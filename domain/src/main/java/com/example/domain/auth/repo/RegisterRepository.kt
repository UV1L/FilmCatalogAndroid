package com.example.domain.auth.repo

import com.example.domain.auth.entities.Resource
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {

    suspend fun register(email: String, username: String, password_1: String, password_2: String): Flow<Resource<String>>
}