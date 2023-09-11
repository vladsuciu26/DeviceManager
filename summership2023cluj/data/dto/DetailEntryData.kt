package com.example.summership2023cluj.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailEntryData(
    @SerialName("id")
    val id: Int,

    @SerialName("brand")
    val brand: String,

    @SerialName("model")
    val model: String,

    @SerialName("pc_name")
    val pcName: String,

    @SerialName("serial_number")
    val serialNumber: String,

    @SerialName("cpu")
    val cpu: String,

    @SerialName("ram")
    val ram: String,

    @SerialName("hdd1")
    val hdd: String,

    @SerialName("project")
    val project: String,

    @SerialName("manufacture_date")
    val manufactureDate: String,

    @SerialName("comment")
    val comment: String

)