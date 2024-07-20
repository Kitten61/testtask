package com.example.myapplication.composables.check_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.usecases.auth.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CheckCodeViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectFlow: Flow<SideEffect>
        get() = _sideEffectChannel.receiveAsFlow()

    fun checkCode(phone: String, code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = authUseCase.checkCode(code = code, phone = phone)
            when (result) {
                is AuthUseCase.CheckCodeResult.Error -> {
                    result.throwable?.let {
                        _sideEffectChannel.send(SideEffect.ShowError(it))
                    }
                }
                is AuthUseCase.CheckCodeResult.Success -> {
                    val sideEffect = if (result.isUserExists) {
                        SideEffect.NavigateToContent
                    } else {
                        SideEffect.NavigateToRegistration
                    }
                    _sideEffectChannel.send(sideEffect)
                }
            }
        }
    }

    sealed class SideEffect {
        data object NavigateToRegistration : SideEffect()
        data object NavigateToContent : SideEffect()
        data class ShowError(
            val throwable: Throwable
        ) : SideEffect()
    }
}
