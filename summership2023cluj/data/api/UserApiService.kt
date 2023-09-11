package com.example.summership2023cluj.data.api

import com.example.summership2023cluj.data.Constants
import com.example.summership2023cluj.data.dto.BaseResponse
import com.example.summership2023cluj.data.dto.User
import com.example.summership2023cluj.data.dto.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("${Constants.AUTHENTICATION}/login")
    suspend fun login(@Body user: User): Response<BaseResponse<UserData>>
}