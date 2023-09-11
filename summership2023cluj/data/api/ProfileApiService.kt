package com.example.summership2023cluj.data.api

import com.example.summership2023cluj.data.Constants
import com.example.summership2023cluj.data.dto.LogoutData
import com.example.summership2023cluj.data.dto.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApiService {
    @GET("${Constants.ACCOUNT}/profile/user")
    suspend fun getProfile(): Response<ProfileResponse>

    @POST("${Constants.AUTHENTICATION}/logout")
    suspend fun logout(): Response<LogoutData>
}