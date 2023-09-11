package com.example.summership2023cluj.data.api

import com.example.summership2023cluj.data.Constants
import com.example.summership2023cluj.data.dto.EntriesResponse
import retrofit2.Response
import retrofit2.http.GET
import com.example.summership2023cluj.data.dto.MyEntriesResponse

interface EntriesApiService {
    @GET("${Constants.INVENTORY}/inventory-entries")
    suspend fun getEntries(): Response<EntriesResponse>

    @GET("${Constants.INVENTORY}/inventory-entries/user")
    suspend fun getMyEntries(): Response<MyEntriesResponse>
}