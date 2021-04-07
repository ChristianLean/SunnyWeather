package com.example.sunnyweather.logic

import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

/**
 * 仓库层，用于判断调用方请求的数据从本地数据源获取还是网络获取
 * 单例类
 */
object Repository {
    /**
     * liveData():自动构建并返回一个liveData对象，且在它的代码块中提供一个挂起函数的上下文
     * IO:run in child thread
     */
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                //kotlin内置的Result包装
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        //notice data change
        emit(result)
    }
}