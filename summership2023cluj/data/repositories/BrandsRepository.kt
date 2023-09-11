package com.example.summership2023cluj.data.repositories

import com.example.summership2023cluj.data.api.BrandsApiService
import com.example.summership2023cluj.data.dto.DevicesResponse
import com.example.summership2023cluj.data.retrofit.RetrofitHelper
import retrofit2.Response

class BrandsRepository {
    private val apiService = RetrofitHelper.buildService(BrandsApiService::class.java)

    suspend fun fetchBrandsResponse(): Response<DevicesResponse> {
        return apiService.getBrands()
    }
}