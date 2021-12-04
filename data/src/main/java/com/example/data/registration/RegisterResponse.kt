package com.example.data.registration

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @SerializedName("username")
    val username: String
)