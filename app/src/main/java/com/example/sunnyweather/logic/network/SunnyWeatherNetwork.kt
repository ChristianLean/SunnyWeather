package com.example.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 统一的网络数据源访问入口
 */
object SunnyWeatherNetwork {
    //返回retrofit构建器实例,动态代理对象
    private val placeService = ServiceCreator.create(PlaceService::class.java)


    /**
     * 协程，挂起 用placeService发起搜索城市数据网络请求
     * 过程：
     * 当外部调用该方法时，retrofit会立即发起网络请求，同时当前协程被阻塞住。
     * 直到服务器响应请求，await（）函数会将解析出来的数据模型对象取出并返回，同时
     * 恢复当前协程的执行。该方法在得到await（）的返回值后会将该数据再返回到上一层
     */
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    //网络请求回调,协程await（）
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}