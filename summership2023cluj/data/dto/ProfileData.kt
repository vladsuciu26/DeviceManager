package com.example.summership2023cluj.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileData(
    @SerialName("id")
    val userID: Int,

    @SerialName("admin")
    val admin: Int,

    @SerialName("firstname")
    val firstname: String,

    @SerialName("lastname")
    val lastname: String,

    @SerialName("mail")
    val mail: String,

    @SerialName("start_date")
    val startDate: String
)
