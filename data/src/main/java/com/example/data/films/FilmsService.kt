package com.example.data.films

import com.example.domain.auth.entities.Film
import com.example.domain.auth.entities.Review
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface FilmsService {

    @GET("get_all")
    fun getAllFilms(@Header("Authorization") token: String): Call<List<Film>>

    @POST("get_reviews")
    fun getAllReviewsForFilm(@Body film: Film): Call<List<Review>>

    @POST("add_review")
    fun addReview(@Body reviewRequest: AddReviewRequest): Call<ResponseBody>
}