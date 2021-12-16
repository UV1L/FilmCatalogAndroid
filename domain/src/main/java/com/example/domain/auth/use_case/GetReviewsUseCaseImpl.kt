package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.entities.Review
import com.example.domain.auth.repo.FilmsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReviewsUseCaseImpl @Inject constructor(private val filmsRepository: FilmsRepository) : ReviewsUseCase.GetReviewsUseCase {

    override suspend fun getReviews(film: Film): Flow<Resource<List<Review>>> {

        return filmsRepository.getReviews(film)
    }
}