package com.example.myapplication.repositories.get_user

import com.example.myapplication.retrofit.response.UserResponse

interface GetUserRepository {

    suspend fun getUser(): UserResponse
}