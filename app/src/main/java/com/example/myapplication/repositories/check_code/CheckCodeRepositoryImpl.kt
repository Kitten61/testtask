package com.example.myapplication.repositories.check_code

import com.example.myapplication.di.qualifiers.NoTokenApi
import com.example.myapplication.retrofit.AuthApi
import com.example.myapplication.retrofit.request.CheckAuthCodeRequest
import com.example.myapplication.retrofit.response.AuthResponse
import javax.inject.Inject

class CheckCodeRepositoryImpl @Inject constructor(
    @NoTokenApi
    val api: AuthApi
) : CheckCodeRepository {

    override suspend fun checkCode(code: String, phone: String): AuthResponse {
        val request = CheckAuthCodeRequest(phone, code)
        val response = api.checkAuthCode(request)
        return response
    }
}