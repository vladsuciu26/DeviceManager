package com.example.summership2023cluj.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyEntriesData(
    @SerialName("id")
    val id: Int,

    @SerialName("brand")
    val brand: String? = null,

    @SerialName("model")
    val model: String,

    @SerialName("is_active")
    val isActive: Boolean
)
