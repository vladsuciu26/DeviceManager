package com.example.summership2023cluj.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val token: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Cookie", "token=$token")
            .build()
        return chain.proceed(request)
    }
}