package com.example.summership2023cluj.data.repositories

import com.example.summership2023cluj.data.api.DevicesApiService
import com.example.summership2023cluj.data.dto.DevicesResponse
import com.example.summership2023cluj.data.retrofit.RetrofitHelper
import retrofit2.Response

class DevicesRepository {
    private val apiService = RetrofitHelper.buildService(DevicesApiService::class.java)

    suspend fun fetchDevicesResponse(): Response<DevicesResponse> {
        return apiService.getDevices()
    }
}