package com.example.domain.auth.use_case

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.entities.Review
import com.example.domain.auth.entities.User
import kotlinx.coroutines.flow.Flow

interface ReviewsUseCase {

    interface GetReviewsUseCase {

        suspend fun getReviews(film: Film): Flow<Resource<List<Review>>>
    }

    interface AddReviewUseCase {

        suspend fun addReview(review: Review): Flow<Resource<Nothing>>
    }
}