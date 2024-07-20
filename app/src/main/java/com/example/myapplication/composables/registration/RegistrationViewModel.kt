package com.example.myapplication.composables.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.composables.check_code.CheckCodeViewModel
import com.example.myapplication.usecases.auth.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _sideEffectChannel = Channel<RegistrationViewModel.SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectFlow: Flow<RegistrationViewModel.SideEffect>
        get() = _sideEffectChannel.receiveAsFlow()

    fun register(phone: String, username: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {

        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = authUseCase.register(
                phone = phone,
                username = username,
                name = name
            )
            when (result) {
                is AuthUseCase.RegistrationResult.Error -> {
                    result.throwable?.let {
                        _sideEffectChannel.send(SideEffect.ShowError(it))
                    }
                }
                AuthUseCase.RegistrationResult.Success -> _sideEffectChannel.send(SideEffect.NavigateToContent)
            }
        }
    }

    sealed class SideEffect {
        data object NavigateToContent : SideEffect()
        data class ShowError(
            val throwable: Throwable
        ) : SideEffect()
    }
}