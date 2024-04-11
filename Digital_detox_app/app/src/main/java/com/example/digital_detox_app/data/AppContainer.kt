package com.example.digital_detox_app.data

import com.example.digital_detox_app.BuildConfig
import com.example.digital_detox_app.network.DigitalDetoxApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


interface AppContainer {
    val leaderboardRepository: DigitalDetoxLeaderboardRepository
}

class DefaultAppContainer: AppContainer {

    private val BASE_URL =
        "https://osdi8mmw61.execute-api.ap-southeast-1.amazonaws.com"

    private val token = BuildConfig.APIKEY

    var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(newRequest)
    }).build()

    private val retrofit = Retrofit.Builder()
        .client(client)
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