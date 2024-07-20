package com.example.myapplication.repositories.register

import com.example.myapplication.di.qualifiers.NoTokenApi
import com.example.myapplication.retrofit.AuthApi
import com.example.myapplication.retrofit.request.RegisterUserRequest
import com.example.myapplication.retrofit.response.AuthResponse
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    @NoTokenApi
    private val api: AuthApi
) : RegistrationRepository {

    override suspend fun register(
        phone: String,
        username: String,
        name: String
    ): AuthResponse {
        val request = RegisterUserRequest(
            phone = phone,
            username = username,
            name = name
        )
        return api.registerUser(request)
    }
}