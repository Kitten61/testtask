package com.example.myapplication.retrofit.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CheckAuthCodeRequest(
    @Json(name = "phone") val phone: String,
    @Json(name = "code") val code: String
)