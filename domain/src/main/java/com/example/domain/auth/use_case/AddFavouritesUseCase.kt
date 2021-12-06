package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Resource
import kotlinx.coroutines.flow.Flow

interface AddFavouritesUseCase {

    suspend fun addFavourite(token: String, filmId: Int): Flow<Resource<Nothing>>
}