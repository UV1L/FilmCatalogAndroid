package com.example.data.user

import com.google.gson.annotations.SerializedName

data class AddFavouriteRequest(

    @SerializedName("token")
    val token: String,
    @SerializedName("filmId")
    val filmId: Int
)