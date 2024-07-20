package com.example.myapplication.retrofit.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SendAuthCodeResponse(
    @Json(name = "is_success") val isSuccess: Boolean
)