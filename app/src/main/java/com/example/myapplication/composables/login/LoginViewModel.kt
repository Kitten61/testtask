package com.example.myapplication.composables.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.usecases.auth.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectFlow: Flow<SideEffect>
        get() = _sideEffectChannel.receiveAsFlow()

    fun sendCode(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (authUseCase.sendCode(phone = phone)) {
                is AuthUseCase.SendCodeResult.Error -> _sideEffectChannel.send(SideEffect.ShowError)
                AuthUseCase.SendCodeResult.Success -> _sideEffectChannel.send(SideEffect.NavigateToCheckCode)
            }
        }
    }

    sealed class SideEffect {
        data object NavigateToCheckCode : SideEffect()
        data object ShowError : SideEffect()
    }
}
