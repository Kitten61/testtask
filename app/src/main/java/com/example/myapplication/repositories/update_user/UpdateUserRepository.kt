package com.example.myapplication.repositories.update_user

import com.example.myapplication.retrofit.response.UpdateUserResponse

interface UpdateUserRepository {

    suspend fun updateUser(name: String, city: String, username: String): UpdateUserResponse
}