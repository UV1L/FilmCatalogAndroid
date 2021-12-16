package com.example.data.films

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Resource
import com.example.domain.auth.entities.Review
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

    override suspend fun getReviews(film: Film): Flow<Resource<List<Review>>> {

        return flow {
            emit(Resource.Loading<List<Review>>())

            val response = filmsService.getAllReviewsForFilm(film).execute()
            if (response.body() != null) {
                emit(Resource.Success(response.body()!!))
            }
            else {
                emit(
                    Resource.Error<List<Review>>("Something went wrong" + response.errorBody()?.string())
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun sendReview(review: Review): Flow<Resource<Nothing>> {

        return flow {

            val response = filmsService.addReview(AddReviewRequest(review.film.id, review.reviewText, review.user.userId, review.rating)).execute()
            if (response.body() != null) {
                emit(Resource.Success(null))
            }
            else {
                emit(
                    Resource.Error<Nothing>("Something went wrong" + response.errorBody()?.string())
                )
            }
        }.flowOn(Dispatchers.IO)
    }


}