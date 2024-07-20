package com.example.myapplication.repositories.refresh_token

import com.example.myapplication.retrofit.RefreshTokenApi
import com.example.myapplication.retrofit.request.RefreshTokenRequest
import com.example.myapplication.retrofit.response.RefreshTokenResponse
import javax.inject.Inject

class RefreshTokenRepositoryImpl @Inject constructor(
    private val userApi: RefreshTokenApi
) : RefreshTokenRepository {

    override suspend fun auth(refreshToken: String): RefreshTokenResponse {
        val request = RefreshTokenRequest(refreshToken)
        return userApi.refreshToken(request)
    }

}