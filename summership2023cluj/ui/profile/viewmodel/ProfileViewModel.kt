package com.example.summership2023cluj.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summership2023cluj.data.repositories.ProfileRepository
import com.example.summership2023cluj.ui.login.viewmodel.states.LogoutState
import com.example.summership2023cluj.ui.login.viewmodel.states.LogoutStateWrapper
import com.example.summership2023cluj.ui.login.viewmodel.states.ProfileState
import com.example.summership2023cluj.ui.login.viewmodel.states.ProfileStateWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel() : ViewModel() {
    private val _uiProfileState = MutableStateFlow(ProfileStateWrapper(null))
    val uiProfileState: StateFlow<ProfileStateWrapper?> = _uiProfileState.asStateFlow()
    private val _uiLogoutState = MutableStateFlow(LogoutStateWrapper(null))
    val uiLogoutState: StateFlow<LogoutStateWrapper?> = _uiLogoutState.asStateFlow()

    private val repo: ProfileRepository = ProfileRepository()
    fun loadProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val profile = repo.fetchProfileResponse()
            if (profile.body()?.success == true) {
                _uiProfileState.update { currentState ->
                    currentState.copy(
                        profileState = ProfileState(
                            id = profile.body()?.data?.userID,
                            firstname = profile.body()?.data?.firstname,
                            lastname = profile.body()?.data?.lastname,
                            mail = profile.body()?.data?.mail,
                            startDate = profile.body()?.data?.startDate,
                            admin = profile.body()?.data?.admin
                        )
                    )
                }
            }

        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            val logoutResponse = repo.logout()
            if (logoutResponse.body()?.success == true) {
                _uiLogoutState.update { currentState ->
                    currentState.copy(
                        logoutState = LogoutState(
                            success = logoutResponse.body()?.success
                        )
                    )
                }
            }
        }
    }
}