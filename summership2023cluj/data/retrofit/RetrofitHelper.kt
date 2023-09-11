package com.example.summership2023cluj.data.retrofit

import android.util.Log
import com.example.summership2023cluj.MyApplication
import com.example.summership2023cluj.data.Constants
import com.example.summership2023cluj.data.interceptors.TokenInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object RetrofitHelper {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }

    private val contentType = MediaType.parse("application/json")!!
    private lateinit var retrofit: Retrofit
    init{
        initiateRetrofit()
    }

    fun initiateRetrofit(){
        retrofit = Retrofit.Builder()
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(Constants.HOST)
            .build()
    }
    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getUnsafeOkHttpClient(): OkHttpClient {
        Log.d("Debugging", "getUnsafeOkHttpClient")
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCertificates = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}

                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCertificates, java.security.SecureRandom())
            val builder = OkHttpClient.Builder()
            //TODO unsafe solution but fast for now:))), will replace it we have time
            runBlocking {
                val token = MyApplication.dataStore.getToken()
                token?.let {
                    Log.d("Debugging", "add interceptor")
                    Log.d("Debugging", token)
                    builder.addInterceptor(TokenInterceptor(token))
                }
            }
            builder.sslSocketFactory(
                sslContext.socketFactory,
                trustAllCertificates[0] as X509TrustManager
            )
            builder.hostnameVerifier { hostname, session -> true }
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}