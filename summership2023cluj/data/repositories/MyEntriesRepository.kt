package com.example.summership2023cluj.data.repositories

import com.example.summership2023cluj.data.api.EntriesApiService
import com.example.summership2023cluj.data.dto.MyEntriesResponse
import com.example.summership2023cluj.data.retrofit.RetrofitHelper
import retrofit2.Response

class MyEntriesRepository {
    init {
        RetrofitHelper.initiateRetrofit()
    }
    private val apiService = RetrofitHelper.buildService(EntriesApiService::class.java)
    suspend fun fetchMyEntriesResponse(): Response<MyEntriesResponse> {
        return apiService.getMyEntries()
    }
}