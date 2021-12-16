package com.example.data.films

import com.google.gson.annotations.SerializedName

data class AddReviewRequest(

    @SerializedName("film")
    val filmId: Int,

    @SerializedName("review")
    val review: String,

    @SerializedName("user")
    var user: Int,

    @SerializedName("rating")
    var rating: Float
)