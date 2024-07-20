package com.example.myapplication.usecases.auth

import android.content.SharedPreferences
import com.example.myapplication.di.qualifiers.RetrofitRepo
import com.example.myapplication.repositories.check_code.CheckCodeRepository
import com.example.myapplication.repositories.register.RegistrationRepository
import com.example.myapplication.repositories.send_code.SendAuthCodeRepository
import com.example.myapplication.repositories.shared_preferences.SharedPreferencesRepository
import com.example.myapplication.utils.Const
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    @RetrofitRepo
    private val sendAuthCodeRepository: SendAuthCodeRepository,
    @RetrofitRepo
    private val checkCodeRepository: CheckCodeRepository,
    @RetrofitRepo
    private val registrationRepository: RegistrationRepository,
    private val sharedPreferences: SharedPreferencesRepository
) : AuthUseCase {

    override suspend fun sendCode(phone: String): AuthUseCase.SendCodeResult {
        return try {
            val sendCodeResult = sendAuthCodeRepository.sendCode(phone)
            if (sendCodeResult.isSuccess) {
                AuthUseCase.SendCodeResult.Success
            } else {
                AuthUseCase.SendCodeResult.Error(null)
            }
        } catch (e: Exception) {
            AuthUseCase.SendCodeResult.Error(e)
        }
    }

    override suspend fun checkCode(code: String, phone: String): AuthUseCase.CheckCodeResult {
        return try {
            val checkCodeResult = checkCodeRepository.checkCode(code, phone)
            if (checkCodeResult.isUserExists == true) {
                sharedPreferences.refreshToken = checkCodeResult.refreshToken
                sharedPreferences.accessToken = checkCodeResult.accessToken
            }
            AuthUseCase.CheckCodeResult.Success(checkCodeResult.isUserExists ?: false)
        } catch (e: Exception) {
            AuthUseCase.CheckCodeResult.Error(e)
        }
    }

    override suspend fun register(phone: String, name: String, username: String): AuthUseCase.RegistrationResult {
        return try {
            val checkCodeResult = registrationRepository.register(phone, username, name)
            sharedPreferences.refreshToken = checkCodeResult.refreshToken
            sharedPreferences.accessToken = checkCodeResult.accessToken
            AuthUseCase.RegistrationResult.Success
        } catch (e: Exception) {
            AuthUseCase.RegistrationResult.Error(e)
        }
    }
}