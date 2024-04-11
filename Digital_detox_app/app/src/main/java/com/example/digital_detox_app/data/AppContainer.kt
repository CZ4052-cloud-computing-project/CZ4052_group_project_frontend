package com.example.digital_detox_app.data

import com.example.digital_detox_app.network.DigitalDetoxApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

interface AppContainer {
    val leaderboardRepository: DigitalDetoxLeaderboardRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL =
        "https://osdi8mmw61.execute-api.ap-southeast-1.amazonaws.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: DigitalDetoxApiService by lazy {
        retrofit.create(DigitalDetoxApiService::class.java)
    }

    override val leaderboardRepository: DigitalDetoxLeaderboardRepository by lazy {
        NetworkDigitalDetoxLeaderboardRepository(retrofitService)
    }
}