package com.example.data.auth

import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    fun auth(@Body authDto: AuthRequest): Call<AuthResponse>
}