package com.example.domain.auth.entities

import com.google.gson.annotations.SerializedName

data class Genre(

    @SerializedName("id")
    val genreId: Int,

    @SerializedName("genreName")
    val genreName: String
)