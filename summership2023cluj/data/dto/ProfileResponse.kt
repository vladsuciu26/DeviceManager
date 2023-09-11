package com.example.summership2023cluj.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    @SerialName("success")
    val success: Boolean,

    @SerialName("data")
    val data: ProfileData
)
