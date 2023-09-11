package com.example.summership2023cluj.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    @SerializedName("name")
    val name: String,

    @SerializedName("value")
    val value: String,

    @SerializedName("expires")
    val expires: Int,

    @SerializedName("domain")
    val domain: String
)
