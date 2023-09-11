package com.example.summership2023cluj.data.api

import com.example.summership2023cluj.data.Constants
import com.example.summership2023cluj.data.dto.EntryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EntryApiService {
    @GET("${Constants.INVENTORY}/inventory-entries/{id}")
    suspend fun getDeviceDetail(@Path("id") id: Int): Response<EntryResponse>
}