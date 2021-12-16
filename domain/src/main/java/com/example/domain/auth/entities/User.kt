package com.example.domain.auth.entities

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("id")
    val userId: Int,

    @SerializedName("username")
    val username: String,

    @SerializedName("token")
    val token: String?
) {

    companion object {

        fun toUser(id: Int, username: String, token: String) = User(id, username, token)
    }
}