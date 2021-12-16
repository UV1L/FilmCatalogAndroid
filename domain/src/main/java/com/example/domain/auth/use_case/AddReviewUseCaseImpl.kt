package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.entities.Review
import com.example.domain.auth.entities.User
import com.example.domain.auth.repo.FilmsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddReviewUseCaseImpl @Inject constructor(private val filmsRepository: FilmsRepository) : ReviewsUseCase.AddReviewUseCase {

    override suspend fun addReview(
        review: Review
    ): Flow<Resource<Nothing>> {

        return filmsRepository.sendReview(review)
    }
}