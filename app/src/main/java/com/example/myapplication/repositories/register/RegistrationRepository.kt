package com.example.myapplication.repositories.register

import com.example.myapplication.retrofit.response.AuthResponse

interface RegistrationRepository {

    suspend fun register(phone: String, username: String, name: String): AuthResponse
}