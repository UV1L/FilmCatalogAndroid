package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import kotlinx.coroutines.flow.Flow

interface FilmsUseCase {

    suspend fun getFilms(token: String): Flow<Resource<List<Film>>>
}