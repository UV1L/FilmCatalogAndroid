package com.example.data.auth

import com.google.gson.annotations.SerializedName

data class AuthRequest(

    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String
)
