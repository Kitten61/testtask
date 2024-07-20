package com.example.myapplication.retrofit

import com.example.myapplication.retrofit.request.UpdateUserRequest
import com.example.myapplication.retrofit.response.ProfileResponse
import com.example.myapplication.retrofit.response.UpdateUserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApi {

    @GET("users/me/")
    suspend fun getCurrentUser(): ProfileResponse

    @PUT("users/me")
    suspend fun updateUser(@Body body: UpdateUserRequest): UpdateUserResponse
}