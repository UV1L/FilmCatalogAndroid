package com.example.data.auth

import com.example.domain.auth.entities.Resource
import com.example.domain.auth.repo.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import androidx.lifecycle.*
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {

    override suspend fun auth(username: String, password: String): Flow<Resource<String>> {

        return flow {
            emit(Resource.Loading<String>())


            delay(1000)
            emit(Resource.Success("token"))
//            val response = authService.auth(AuthRequest(username, password)).execute()
//            try {
//                emit(
//                    Resource.Success(response.body()!!.token)
//                )
//            } catch (e: HttpException) {
//                emit(Resource.Error<String>(e.localizedMessage ?: "Something went wrong"))
//            } catch (e: IOException) {
//                emit(Resource.Error<String>("Couldn't reach server"))
//            }
        }.flowOn(Dispatchers.IO)
    }
}