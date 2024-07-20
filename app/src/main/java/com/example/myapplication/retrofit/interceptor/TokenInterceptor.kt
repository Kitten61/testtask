package com.example.myapplication.retrofit.interceptor

import com.example.myapplication.repositories.shared_preferences.SharedPreferencesRepository
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.HttpURLConnection
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferencesRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = sharedPreferences.accessToken
        val request = chain.request()
        return try {
            if (accessToken != "") {
                chain.proceed(
                    request
                        .newBuilder()
                        .addHeader("Authorization", "Bearer $accessToken")
                        .addHeader("Accept", "*/*")
                        .build()
                )
            } else {
                chain.proceed(request)
            }
        } catch (e: Exception) {
            val message = e.message
            e.printStackTrace()
            Response.Builder()
                .body((message ?: "").toResponseBody("-".toMediaTypeOrNull()))
                .protocol(Protocol.HTTP_2)
                .code(HttpURLConnection.HTTP_NOT_FOUND)
                .request(request)
                .message(message ?: "")
                .build()
        }
    }
}
