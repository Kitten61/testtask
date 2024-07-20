package com.example.myapplication.retrofit.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name="name")val name: String,
    @Json(name="username")val username: String,
    @Json(name="birthday")val birthday: String?,
    @Json(name="city")val city: String?,
    @Json(name="vk")val vk: String?,
    @Json(name="instagram")val instagram: String?,
    @Json(name="status")val status: String?,
    @Json(name="avatar")val avatar: String?,
    @Json(name="id")val id: Int,
    @Json(name="last")val last: String?,
    @Json(name="online")val online: Boolean,
    @Json(name="created")val created: String,
    @Json(name="phone")val phone: String,
    @Json(name="completed_task")val completedTask: Int,
    @Json(name="avatars")val avatars: AvatarsResponse?,
)