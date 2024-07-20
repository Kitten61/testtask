package com.example.myapplication.repositories.check_code

import com.example.myapplication.retrofit.response.AuthResponse

interface CheckCodeRepository {

    suspend fun checkCode(code: String, phone: String): AuthResponse
}