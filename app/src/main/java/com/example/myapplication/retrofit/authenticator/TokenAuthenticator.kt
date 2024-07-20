package com.example.myapplication.retrofit.authenticator

import com.example.myapplication.di.qualifiers.RetrofitRepo
import com.example.myapplication.repositories.refresh_token.RefreshTokenRepository
import com.example.myapplication.repositories.shared_preferences.SharedPreferencesRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    @RetrofitRepo
    private val refreshTokenRepository: RefreshTokenRepository
) : Authenticator {



    override fun authenticate(route: Route?, response: Response): Request {
        return runBlocking {
            val responseBody = response.body.toString()
            val refreshToken = sharedPreferencesRepository.refreshToken ?: ""
            val token = when {
                else -> try {
                    val data = refreshTokenRepository.auth(refreshToken = refreshToken)
                    sharedPreferencesRepository.refreshToken = data.refreshToken
                    sharedPreferencesRepository.accessToken = data.accessToken
                    data.accessToken
                } catch (e: Throwable) {
                    e.printStackTrace()
                    null
                }
            }
            response.request
                .newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        }

    }
}
