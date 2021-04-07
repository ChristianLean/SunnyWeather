package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        //财运天气令牌
        const val TOKEN = "NDvSUkFlnb05PIev"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}