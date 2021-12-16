package com.example.data.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse (

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("username")
    val username: String,

    @SerializedName("token")
    val token: String
)