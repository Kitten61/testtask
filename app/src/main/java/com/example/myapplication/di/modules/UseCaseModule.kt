package com.example.myapplication.di.modules

import com.example.myapplication.usecases.auth.AuthUseCase
import com.example.myapplication.usecases.auth.AuthUseCaseImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule::class])
interface UseCaseModule {

    @Binds
    fun authUseCase(authUseCase: AuthUseCaseImpl): AuthUseCase
}