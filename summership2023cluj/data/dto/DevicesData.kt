package com.example.summership2023cluj.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DevicesData(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String
)