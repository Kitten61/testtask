package com.example.myapplication.repositories.get_user

import com.example.myapplication.retrofit.UserApi
import com.example.myapplication.retrofit.response.UserResponse
import javax.inject.Inject

class GetUserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : GetUserRepository {

    override suspend fun getUser() : UserResponse{
        return api.getCurrentUser().user
    }
}