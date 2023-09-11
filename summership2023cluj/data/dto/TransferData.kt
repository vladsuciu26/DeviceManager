package com.example.summership2023cluj.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransferData(
    @SerialName("currentHolder")
    val currentHolder: HolderData,

    @SerialName("nextHolder")
    val nextHolder: HolderData
)
