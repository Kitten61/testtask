package com.example.myapplication.repositories.send_code


import com.example.myapplication.retrofit.response.SendAuthCodeResponse

interface SendAuthCodeRepository {

    suspend fun sendCode(phone: String): SendAuthCodeResponse
}