package com.example.summership2023cluj.data.api

import com.example.summership2023cluj.data.Constants
import com.example.summership2023cluj.data.dto.TransferResponse
import retrofit2.Response
import retrofit2.http.GET

interface TransfersApiService {
    @GET("${Constants.INVENTORY}/inventory-entries/pending-transfers/")
    suspend fun getPendingTransfers(): Response<TransferResponse>
}