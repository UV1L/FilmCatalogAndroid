package com.example.domain.auth.repo

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import kotlinx.coroutines.flow.Flow
import java.net.http.HttpResponse

interface UserRepository {

    suspend fun addFavourite(token: String, filmId: Int): Flow<Resource<Nothing>>

    suspend fun getFavourites(token: String): Flow<Resource<Set<Film>>>
}