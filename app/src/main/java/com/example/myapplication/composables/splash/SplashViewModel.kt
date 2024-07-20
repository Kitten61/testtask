package com.example.myapplication.composables.splash

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repositories.shared_preferences.SharedPreferencesRepository
import com.example.myapplication.utils.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@SplashScope
class SplashViewModel @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    var splashState: SplashState by mutableStateOf(SplashState.Loading)

    init {
        viewModelScope.launch(Dispatchers.Main) {
            val accessToken = sharedPreferencesRepository.accessToken
            splashState = if (accessToken == null) {
                SplashState.Auth
            } else {
                SplashState.Content
            }
        }
    }

}

enum class SplashState {
    Auth,
    Content,
    Loading
}