package com.example.myapplication.repositories.shared_preferences

import android.content.SharedPreferences
import com.example.myapplication.utils.Const
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPreferencesRepository {

    override var accessToken: String?
        get() = sharedPreferences.getString(Const.PREFERENCES_ACCESS_TOKEN, null)
        set(data) = sharedPreferences
                .edit()
                .putString(Const.PREFERENCES_ACCESS_TOKEN, data)
                .apply()


    override var refreshToken: String?
        get() = sharedPreferences.getString(Const.PREFERENCES_REFRESH_TOKEN, null)
        set(data) = sharedPreferences
            .edit()
            .putString(Const.PREFERENCES_REFRESH_TOKEN, data)
            .apply()
}