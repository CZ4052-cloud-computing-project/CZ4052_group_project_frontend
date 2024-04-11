package com.example.digital_detox_app.network

import com.example.digital_detox_app.data.LeaderBoardResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Retrofit service object for creating api calls
 */
interface DigitalDetoxApiService {
    @GET("default/leaderboard")
    suspend fun getLeaderBoardInfo(@Query("date") date: String): LeaderBoardResponse
}
