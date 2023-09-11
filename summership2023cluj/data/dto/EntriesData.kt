package com.example.summership2023cluj.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EntriesData(
    @SerialName("id")
    val id: Int,

    @SerialName("device")
    val device: String? = null,

    @SerialName("brand")
    val brand: String? = null,

    @SerialName("is_active")
    val isActive: Boolean,

    @SerialName("model")
    val model: String,

    @SerialName("user_id")
    val userId: Int? = null,
)