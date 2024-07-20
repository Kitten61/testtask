package com.example.myapplication.repositories.send_code

import com.example.myapplication.di.qualifiers.NoTokenApi
import com.example.myapplication.retrofit.AuthApi
import com.example.myapplication.retrofit.request.SendAuthCodeRequest
import com.example.myapplication.retrofit.response.SendAuthCodeResponse
import javax.inject.Inject

class SendAuthCodeRepositoryImpl @Inject constructor(
    @NoTokenApi
    private val  api: AuthApi
): SendAuthCodeRepository {

    override suspend fun sendCode(phone: String): SendAuthCodeResponse {
        val request = SendAuthCodeRequest(phone)
        return api.sendAuthCode(request)
    }
}