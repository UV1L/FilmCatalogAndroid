package com.example.domain.auth.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Film(
    @SerializedName("filmId")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster")
    val posterPath: String,
    @SerializedName("likedBy")
    val likedBy: Set<User>
) : Serializable