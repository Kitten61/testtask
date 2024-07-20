package com.example.myapplication.repositories.refresh_token

import com.example.myapplication.retrofit.response.RefreshTokenResponse

interface RefreshTokenRepository {

    suspend fun auth(refreshToken: String): RefreshTokenResponse
}