package com.example.myapplication.retrofit.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse(
    @Json(name = "refresh_token") val refreshToken: String?,
    @Json(name = "access_token") val accessToken: String?,
    @Json(name = "user_id") val userId: Int?,
    @Json(name = "is_user_exists") val isUserExists: Boolean?
)