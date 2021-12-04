package com.example.data.registration

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("register")
    fun register(@Body registerDto: RegisterRequest): Call<RegisterResponse>
}