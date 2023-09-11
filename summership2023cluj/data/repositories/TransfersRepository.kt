package com.example.summership2023cluj.data.repositories

import com.example.summership2023cluj.data.api.TransfersApiService
import com.example.summership2023cluj.data.dto.TransferResponse
import com.example.summership2023cluj.data.retrofit.RetrofitHelper
import retrofit2.Response

class TransfersRepository {
    private val apiService = RetrofitHelper.buildService(TransfersApiService::class.java)

    suspend fun fetchTransfers() : Response<TransferResponse> {
        return apiService.getPendingTransfers()
    }
}