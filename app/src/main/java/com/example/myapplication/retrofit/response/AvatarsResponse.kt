package com.example.myapplication.retrofit.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvatarsResponse(
    @Json(name = "avatar") val avatar: String,
    @Json(name = "bigAvatar") val bigAvatar: String,
    @Json(name = "miniAvatar") val miniAvatar: String
)