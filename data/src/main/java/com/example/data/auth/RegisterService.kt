package com.example.data.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("register")
    fun register(@Body registerDto: RegisterRequest): Call<RegisterResponse>
}