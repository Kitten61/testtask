package com.example.myapplication.di

import com.example.myapplication.usecases.auth.AuthUseCase

interface UseCases {

    fun authUseCase(): AuthUseCase
}
