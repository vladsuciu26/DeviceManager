package com.example.summership2023cluj

import android.app.Application
import com.example.summership2023cluj.data.DataStoreManager

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        dataStore = DataStoreManager(this)
    }

    companion object {
        lateinit var dataStore: DataStoreManager
    }
}