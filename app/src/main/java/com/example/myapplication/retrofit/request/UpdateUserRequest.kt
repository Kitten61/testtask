package com.example.myapplication.retrofit.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateUserRequest(
    @Json(name = "name") val name: String,
    @Json(name = "city") val city: String,
    @Json(name = "username") val username: String,
)