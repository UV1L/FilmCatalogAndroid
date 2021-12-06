package com.example.data.user

import com.example.domain.auth.entities.Film
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @POST("add_favourite")
    fun addFavourite(@Body addFavouriteDto: AddFavouriteRequest): Call<ResponseBody>

    @GET("get_favourites")
    fun getFavourites(@Header("token") token: String): Call<Set<Film>>
}