package com.example.myapplication.composables.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.di.qualifiers.RetrofitRepo
import com.example.myapplication.repositories.get_user.GetUserRepository
import com.example.myapplication.repositories.update_user.UpdateUserRepository
import com.example.myapplication.retrofit.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(
    @RetrofitRepo
    private val getUserRepository: GetUserRepository,
    @RetrofitRepo
    private val updateUserRepository: UpdateUserRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState>
        get() = _uiState

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(UiState.Loading)
            try {
                val data = getUserRepository.getUser()
                _uiState.emit(UiState.EditProfile(data))
            } catch (e: Exception) {
                _uiState.emit(UiState.Error(e))
            }
        }
    }

    fun updateProfile(name: String, city: String, username: String) {
        viewModelScope.launch {
            try {
                updateUserRepository.updateUser(name, city, username)
                val state = uiState.value as UiState.EditProfile
                _uiState.emit(
                    state.copy(
                        userResponse = state.userResponse.copy(
                            name = name,
                            city = city,
                            username = username
                        )
                    )
                )
            } catch (e: Exception) {
                _uiState.emit(UiState.Error(e))
            }

        }
    }

    sealed class UiState {
        data object Loading : UiState()
        data class EditProfile(
            val userResponse: UserResponse
        ) : UiState()

        data class Error(
            val throwable: Throwable
        ) : UiState()
    }
}