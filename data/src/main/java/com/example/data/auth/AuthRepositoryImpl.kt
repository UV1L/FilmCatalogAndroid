package com.example.data.auth

import com.example.domain.auth.entities.Resource
import com.example.domain.auth.repo.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import androidx.lifecycle.*
import com.example.domain.auth.entities.User
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {

    override suspend fun auth(username: String, password: String): Flow<Resource<User>> {

        return flow {
            emit(Resource.Loading<User>())

            val response = authService.auth(AuthRequest(username, password)).execute()
            if (response.body() != null) {
                emit(
                    Resource.Success<User>(
                        User.toUser(
                            response.body()!!.userId,
                            response.body()!!.username,
                            response.body()!!.token
                        )
                    )
                )
            }
            else {
                emit(
                    Resource.Error<User>("Something went wrong" + response.errorBody()?.string())
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}