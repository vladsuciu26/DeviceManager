package com.example.summership2023cluj.ui.login.viewmodel.states

data class ProfileState(
    val id: Int? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val mail: String? = null,
    val startDate:String? = null,
    val admin: Int? = null
)

data class ProfileStateWrapper(
    val profileState: ProfileState?
)
