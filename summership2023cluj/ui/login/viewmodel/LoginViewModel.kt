package com.example.summership2023cluj.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summership2023cluj.data.dto.User
import com.example.summership2023cluj.data.repositories.LoginRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    private val repo = LoginRepository()
    private val mutableState = MutableStateFlow(LoginState())
    val viewModelState: StateFlow<LoginState> = mutableState

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        mutableState.value = LoginState(false)
    }

    class LoginState(val isLogin: Boolean? = null)

    fun authenticate(user: User) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            repo.login(user)
            mutableState.value = LoginState(true)
        }
    }
}