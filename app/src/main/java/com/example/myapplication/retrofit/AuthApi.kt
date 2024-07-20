package com.example.myapplication.retrofit

import com.example.myapplication.retrofit.request.CheckAuthCodeRequest
import com.example.myapplication.retrofit.request.RegisterUserRequest
import com.example.myapplication.retrofit.request.SendAuthCodeRequest
import com.example.myapplication.retrofit.response.AuthResponse
import com.example.myapplication.retrofit.response.SendAuthCodeResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("users/send-auth-code/")
    suspend fun sendAuthCode(@Body body: SendAuthCodeRequest): SendAuthCodeResponse

    @POST("users/check-auth-code/")
    suspend fun checkAuthCode(@Body body: CheckAuthCodeRequest): AuthResponse

    @POST("users/register/")
    suspend fun registerUser(@Body body: RegisterUserRequest): AuthResponse
}