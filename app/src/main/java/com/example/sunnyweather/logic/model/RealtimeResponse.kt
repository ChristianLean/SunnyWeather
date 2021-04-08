package com.example.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ChristianL on 4/7/21
 * realtime weather model
 */
data class RealtimeResponse(val status: String, val result: Result) {
    /**
     * definite in inner class can avoid same name conflict
     */
    data class Result(val realtime: Realtime)
    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}


