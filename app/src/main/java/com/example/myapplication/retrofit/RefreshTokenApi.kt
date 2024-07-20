package com.example.myapplication.retrofit

import com.example.myapplication.retrofit.request.RefreshTokenRequest
import com.example.myapplication.retrofit.response.RefreshTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenApi {


    @POST("users/refresh-token/")
    suspend fun refreshToken(@Body body: RefreshTokenRequest): RefreshTokenResponse
}