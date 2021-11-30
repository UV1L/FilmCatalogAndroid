package com.example.data.auth

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @SerializedName("username")
    val username: String
)