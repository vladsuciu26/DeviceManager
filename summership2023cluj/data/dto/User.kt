package com.example.summership2023cluj.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerializedName("id")
    val id: String,

    @SerializedName("password")
    val password: String
)
