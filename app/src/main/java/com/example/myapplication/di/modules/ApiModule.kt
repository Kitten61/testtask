package com.example.myapplication.di.modules

import com.example.myapplication.di.qualifiers.NoTokenApi
import com.example.myapplication.di.qualifiers.TokenApi
import com.example.myapplication.retrofit.AuthApi
import com.example.myapplication.retrofit.RefreshTokenApi
import com.example.myapplication.retrofit.UserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ApiModule {

    @Provides
    @Singleton
    @NoTokenApi
    fun provideAuthApi(
        @NoTokenApi
        retrofit: Retrofit
    ): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTokenApi(
        @TokenApi
        retrofit: Retrofit
    ): RefreshTokenApi {
        return retrofit.create(RefreshTokenApi::class.java)
    }
}