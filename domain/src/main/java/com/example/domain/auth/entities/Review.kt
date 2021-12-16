package com.example.domain.auth.entities

import com.google.gson.annotations.SerializedName

data class Review(

    @SerializedName("film")
    val film: Id,

    @SerializedName("review")
    val reviewText: String,

    @SerializedName("user")
    val user: User,

    @SerializedName("rating")
    val rating: Float
)