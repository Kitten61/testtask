package com.example.myapplication.repositories.update_user

import com.example.myapplication.retrofit.UserApi
import com.example.myapplication.retrofit.request.UpdateUserRequest
import com.example.myapplication.retrofit.response.UpdateUserResponse
import javax.inject.Inject

class UpdateUserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UpdateUserRepository {

    override suspend fun updateUser(name: String, city: String, username: String): UpdateUserResponse {
        val request = UpdateUserRequest(
            name = name,
            city = city,
            username = username
        )
        return api.updateUser(request)
    }
}