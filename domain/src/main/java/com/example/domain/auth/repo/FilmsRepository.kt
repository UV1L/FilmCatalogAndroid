package com.example.domain.auth.repo

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.entities.Review
import com.example.domain.auth.entities.User
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    suspend fun getFilms(token: String): Flow<Resource<List<Film>>>

    suspend fun getReviews(film: Film): Flow<Resource<List<Review>>>

    suspend fun sendReview(review: Review): Flow<Resource<Nothing>>
}