package com.example.summership2023cluj.data.repositories

import com.example.summership2023cluj.MyApplication
import com.example.summership2023cluj.data.api.UserApiService
import com.example.summership2023cluj.data.dto.User
import com.example.summership2023cluj.data.dto.UserData
import com.example.summership2023cluj.data.handler.validateResponse
import com.example.summership2023cluj.data.retrofit.RetrofitHelper

class LoginRepository() {
    private val apiService = RetrofitHelper.buildService(UserApiService::class.java)

    suspend fun login(user: User): UserData {
        val response = apiService.login(user).validateResponse()
        MyApplication.dataStore.saveToken(response.value)
        return response
    }
}