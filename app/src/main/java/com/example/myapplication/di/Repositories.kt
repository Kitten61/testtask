package com.example.myapplication.di

import com.example.myapplication.di.qualifiers.RetrofitRepo
import com.example.myapplication.repositories.check_code.CheckCodeRepository
import com.example.myapplication.repositories.get_user.GetUserRepository
import com.example.myapplication.repositories.refresh_token.RefreshTokenRepository
import com.example.myapplication.repositories.register.RegistrationRepository
import com.example.myapplication.repositories.send_code.SendAuthCodeRepository
import com.example.myapplication.repositories.shared_preferences.SharedPreferencesRepository
import com.example.myapplication.repositories.update_user.UpdateUserRepository

interface Repositories {

    @RetrofitRepo
    fun checkCodeRepository(): CheckCodeRepository

    @RetrofitRepo
    fun sendAuthCodeRepository(): SendAuthCodeRepository

    @RetrofitRepo
    fun registrationRepository(): RegistrationRepository

    @RetrofitRepo
    fun getUserRepository(): GetUserRepository

    @RetrofitRepo
    fun refreshTokenRepository(): RefreshTokenRepository

    @RetrofitRepo
    fun updateUserRepository(): UpdateUserRepository

    fun sharedPreferencesRepository(): SharedPreferencesRepository
}
