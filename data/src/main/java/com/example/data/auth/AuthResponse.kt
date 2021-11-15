package com.example.data.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse (

    @SerializedName("token")
    val token: String
)