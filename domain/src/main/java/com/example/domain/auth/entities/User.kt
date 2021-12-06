package com.example.domain.auth.entities

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("username")
    val username: String
)