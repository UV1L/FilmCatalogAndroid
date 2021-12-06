package com.example.data.user

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(private val userService: UserService) : UserRepository {

    companion object {

        private val ERROR_MESSAGE_PREFIX = "Something went wrong"
    }

    override suspend fun addFavourite(token: String, filmId: Int): Flow<Resource<Nothing>> {

        return flow {

            val response = userService.addFavourite(AddFavouriteRequest(token, filmId)).execute()
            if (response.isSuccessful) {
                emit(Resource.Success<Nothing>(null))
            }
            else {
                emit(
                    Resource.Error<Nothing>(ERROR_MESSAGE_PREFIX + response.errorBody()?.string())
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFavourites(token: String): Flow<Resource<Set<Film>>> {

        return flow {

            emit(Resource.Loading<Set<Film>>())

            val response = userService.getFavourites(token).execute()
            if (response.isSuccessful) {
                emit(Resource.Success<Set<Film>>(response.body()!!))
            }
            else {
                emit(
                    Resource.Error<Set<Film>>(ERROR_MESSAGE_PREFIX + response.errorBody()?.string())
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}