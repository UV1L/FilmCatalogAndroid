package com.example.domain.auth.repo

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    suspend fun getFilms(token: String): Flow<Resource<List<Film>>>
}