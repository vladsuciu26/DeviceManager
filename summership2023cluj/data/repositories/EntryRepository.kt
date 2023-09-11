package com.example.summership2023cluj.data.repositories

import com.example.summership2023cluj.data.api.EntryApiService
import com.example.summership2023cluj.data.dto.EntryResponse
import com.example.summership2023cluj.data.retrofit.RetrofitHelper
import retrofit2.Response

class EntryRepository {
    private val apiService = RetrofitHelper.buildService(EntryApiService::class.java)

    suspend fun fetchEntryResponse(id: Int): Response<EntryResponse> {
        return apiService.getDeviceDetail(id)
    }
}