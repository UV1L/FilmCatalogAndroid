package com.example.data.auth

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

    @SerializedName("email")
    val email: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("password_1")
    val password_1: String,

    @SerializedName("password_2")
    val password_2: String
)