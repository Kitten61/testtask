package com.example.myapplication.usecases.auth

interface AuthUseCase {

    suspend fun sendCode(phone: String): SendCodeResult
    suspend fun checkCode(code: String, phone: String): CheckCodeResult
    suspend fun register(phone: String, name: String, username: String): RegistrationResult

    sealed class RegistrationResult {

        data object Success : RegistrationResult()

        data class Error(
            val throwable: Throwable?
        ) : RegistrationResult()
    }

    sealed class CheckCodeResult {

        data class Success(
            val isUserExists: Boolean
        ) : CheckCodeResult()

        data class Error(
            val throwable: Throwable?
        ) : CheckCodeResult()
    }

    sealed class SendCodeResult {

        data object Success : SendCodeResult()

        data class Error(
            val throwable: Throwable?
        ) : SendCodeResult()
    }
}