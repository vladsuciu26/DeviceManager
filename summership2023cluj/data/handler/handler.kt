package com.example.summership2023cluj.data.handler

import com.example.summership2023cluj.data.BackendException
import com.example.summership2023cluj.data.dto.BaseResponse
import retrofit2.Response


fun <T> Response<BaseResponse<T>>.validateResponse(): T {
    validateResponse(this)
    val responseModel =
        this.body() ?: throw BackendException(this.code().toString(), "Missing body in response")

    if (!responseModel.success) {
        throw BackendException(
            responseModel.error?.code.orEmpty(),
            "Call was not successful with error: ${responseModel.error?.message}"
        )
    }
    return getValidResult(responseModel.data, this.code())
}

fun <R> validateResponse(response: Response<R>) {
    if (!response.isSuccessful) {
        val baseResponse = response.errorBody()?.string().orEmpty()
        throw BackendException(response.code().toString(), response.message().plus(baseResponse))
    }
}

fun <T> getValidResult(body: T?, responseCode: Int): T {
    if (body == null) {
        throw BackendException(responseCode.toString(), "Missing data in responseModel")
    }
    return body
}


