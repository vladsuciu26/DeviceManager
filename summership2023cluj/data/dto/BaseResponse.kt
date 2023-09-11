package com.example.summership2023cluj.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("data")
    val data: T?,

    @SerialName("err")
    val error: ErrorResponse?,

    @SerialName("transaction_id")
    val transactionId: String,

    @SerialName("servertime")
    val serverTime: Long
)


