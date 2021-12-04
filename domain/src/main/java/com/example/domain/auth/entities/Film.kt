package com.example.domain.auth.entities

import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster")
    val posterPath: String
)