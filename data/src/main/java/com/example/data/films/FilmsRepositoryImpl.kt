package com.example.data.films

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.repo.FilmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FilmsRepositoryImpl(private val filmsService: FilmsService) : FilmsRepository {

    override suspend fun getFilms(token: String): Flow<Resource<List<Film>>> {

        return flow {
            emit(Resource.Loading<List<Film>>())

            val response = filmsService.getAllFilms("Bearer_".plus(token)).execute()
            if (response.body() != null) {
                emit(
                    Resource.Success(response.body())
                )
            }
            else {
                emit(
                    Resource.Error<List<Film>>("Something went wrong" + response.errorBody()?.string())
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}