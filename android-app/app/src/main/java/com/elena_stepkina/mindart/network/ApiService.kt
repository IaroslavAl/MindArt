package com.elena_stepkina.mindart.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun generateImage(
        @Query("color1") color1: String,
        @Query("color2") color2: String,
        @Query("color3") color3: String,
        @Query("style") style: String
    ): Response<ResponseBody>
}