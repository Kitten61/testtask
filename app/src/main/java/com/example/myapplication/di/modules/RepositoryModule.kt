package com.example.myapplication.di.modules

import com.example.myapplication.di.qualifiers.RetrofitRepo
import com.example.myapplication.repositories.check_code.CheckCodeRepository
import com.example.myapplication.repositories.check_code.CheckCodeRepositoryImpl
import com.example.myapplication.repositories.get_user.GetUserRepository
import com.example.myapplication.repositories.get_user.GetUserRepositoryImpl
import com.example.myapplication.repositories.refresh_token.RefreshTokenRepository
import com.example.myapplication.repositories.refresh_token.RefreshTokenRepositoryImpl
import com.example.myapplication.repositories.register.RegistrationRepository
import com.example.myapplication.repositories.register.RegistrationRepositoryImpl
import com.example.myapplication.repositories.send_code.SendAuthCodeRepository
import com.example.myapplication.repositories.send_code.SendAuthCodeRepositoryImpl
import com.example.myapplication.repositories.shared_preferences.SharedPreferencesRepository
import com.example.myapplication.repositories.shared_preferences.SharedPreferencesRepositoryImpl
import com.example.myapplication.repositories.update_user.UpdateUserRepository
import com.example.myapplication.repositories.update_user.UpdateUserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [ApiModule::class])
interface RepositoryModule {

    @Binds
    @RetrofitRepo
    fun checkCodeRepository(checkCodeRepository: CheckCodeRepositoryImpl): CheckCodeRepository

    @Binds
    @RetrofitRepo
    fun sendAuthCodeRepository(sendAuthCodeRepository: SendAuthCodeRepositoryImpl): SendAuthCodeRepository

    @Binds
    @RetrofitRepo
    fun registrationRepository(registrationRepository: RegistrationRepositoryImpl): RegistrationRepository

    @Binds
    @RetrofitRepo
    fun getUserRepository(getUserRepository: GetUserRepositoryImpl): GetUserRepository

    @Binds
    @RetrofitRepo
    fun refreshTokenRepository(refreshTokenRepository: RefreshTokenRepositoryImpl): RefreshTokenRepository

    @Binds
    @RetrofitRepo
    fun updateUserRepository(updateUserRepository: UpdateUserRepositoryImpl): UpdateUserRepository

    @Binds
    fun sharedPreferencesRepository(registrationRepository: SharedPreferencesRepositoryImpl): SharedPreferencesRepository
}