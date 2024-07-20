package com.example.myapplication.retrofit.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SendAuthCodeRequest(
    val phone: String
)