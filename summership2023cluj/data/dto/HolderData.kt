package com.example.summership2023cluj.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HolderData(
    @SerialName("user_id")
    val userId: Int,

    @SerialName("inventory_id")
    val entryId: Int
)
