package com.example.summership2023cluj.ui.devicemanagement.states

import com.example.summership2023cluj.data.dto.DevicesData

data class DevicesState(
    val listOfDevices: ArrayList<DevicesData>? = null
)

data class DevicesStateWrapper(
    val devicesState: DevicesState?
)