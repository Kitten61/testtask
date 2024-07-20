package com.example.myapplication.retrofit.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileResponse(
    @Json(name = "profile_data") val user: UserResponse
)
