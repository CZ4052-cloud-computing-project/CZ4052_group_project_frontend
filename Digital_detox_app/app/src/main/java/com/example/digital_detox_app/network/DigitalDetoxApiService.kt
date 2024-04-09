package com.example.digital_detox_app.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://osdi8mmw61.execute-api.ap-southeast-1.amazonaws.com"

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface DigitalDetoxApiService {
    @GET("default/leaderboard")
    suspend fun getLeaderBoardInfo(): String
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object DigitalDetoxApi {
    val retrofitService: DigitalDetoxApiService by lazy {
        retrofit.create(DigitalDetoxApiService::class.java)
    }
}
