package com.example.data.films

import com.example.domain.auth.entities.Film
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

import retrofit2.http.Headers

interface FilmsService {

    @GET("get_all")
    fun getAllFilms(@Header("Authorization") token: String): Call<List<Film>>
}