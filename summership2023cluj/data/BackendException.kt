package com.example.summership2023cluj.data

class BackendException(val code: String, val messageCode: String) : Exception() {
    override fun toString(): String {
        return "BackendException(code='$code', messageCode='$messageCode')"
    }
}
