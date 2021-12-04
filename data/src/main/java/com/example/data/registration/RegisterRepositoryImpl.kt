package com.example.data.registration

import com.example.data.registration.RegisterRequest
import com.example.data.registration.RegisterService
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.repo.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegisterRepositoryImpl(private val registerService: RegisterService) : RegisterRepository {

    override suspend fun register(
        email: String,
        username: String,
        password_1: String,
        password_2: String
    ): Flow<Resource<String>> {

        return flow {
            emit(Resource.Loading<String>())

            val response = registerService.register(RegisterRequest(email, username, password_1, password_2)).execute()
            if (response.body()?.username != null) {
                emit(
                    Resource.Success(response.body()?.username)
                )
            }
            else {
                emit(
                    Resource.Error<String>("Something went wrong" + response.errorBody()?.string())
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}