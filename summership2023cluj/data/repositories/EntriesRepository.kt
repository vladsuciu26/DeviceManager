package com.example.summership2023cluj.data.repositories

import com.example.summership2023cluj.data.api.EntriesApiService
import com.example.summership2023cluj.data.dto.EntriesResponse
import com.example.summership2023cluj.data.retrofit.RetrofitHelper
import retrofit2.Response
import com.example.summership2023cluj.data.dto.MyEntriesResponse

class EntriesRepository {
    init {
        RetrofitHelper.initiateRetrofit()
    }
    private val apiService = RetrofitHelper.buildService(EntriesApiService::class.java)

    suspend fun fetchEntriesResponse(): Response<EntriesResponse> {
        return apiService.getEntries()
    }

}