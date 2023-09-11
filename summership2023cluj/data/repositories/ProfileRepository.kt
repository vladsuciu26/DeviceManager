package com.example.summership2023cluj.data.repositories

import com.example.summership2023cluj.MyApplication
import com.example.summership2023cluj.data.api.ProfileApiService
import com.example.summership2023cluj.data.dto.LogoutData
import com.example.summership2023cluj.data.dto.ProfileResponse
import com.example.summership2023cluj.data.retrofit.RetrofitHelper
import retrofit2.Response

class ProfileRepository {
    init {
        RetrofitHelper.initiateRetrofit()
    }
    private val apiService = RetrofitHelper.buildService(ProfileApiService::class.java)

    suspend fun fetchProfileResponse(): Response<ProfileResponse> {
        val response: Response<ProfileResponse> = apiService.getProfile()
        response.body()?.let {
            MyApplication.dataStore.saveProfile(it.data)
        }
        return response
    }

    suspend fun logout(): Response<LogoutData> {
        return apiService.logout()
    }
}