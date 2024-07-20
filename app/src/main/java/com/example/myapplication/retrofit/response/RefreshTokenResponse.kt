package com.example.myapplication.retrofit.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RefreshTokenResponse(
    @Json(name = "refresh_token") val refreshToken: String,
    @Json(name = "access_token") val accessToken: String
)
