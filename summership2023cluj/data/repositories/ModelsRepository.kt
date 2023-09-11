package com.example.summership2023cluj.data.repositories

import com.example.summership2023cluj.data.api.ModelsApiService
import com.example.summership2023cluj.data.dto.DevicesResponse
import com.example.summership2023cluj.data.retrofit.RetrofitHelper
import retrofit2.Response

class ModelsRepository {
    private val apiService = RetrofitHelper.buildService(ModelsApiService::class.java)
    suspend fun fetchModelsResponse(): Response<DevicesResponse> {
        return apiService.getModels()
    }
}