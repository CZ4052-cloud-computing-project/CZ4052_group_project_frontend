package com.example.digital_detox_app.network

import com.example.digital_detox_app.data.LeaderBoardResponse
import com.example.digital_detox_app.data.StatisticEntry
import com.example.digital_detox_app.data.TimerInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Retrofit service object for creating api calls
 */
interface DigitalDetoxApiService {
    @GET("default/leaderboard")
    suspend fun getLeaderBoardInfo(@Query("date") date: String): LeaderBoardResponse

    @GET("default/statistics")
    suspend fun getStatisticsInfo(
        @Query("username") username: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
    ): List<StatisticEntry>

    @POST("default/sessions")
    suspend fun sendTimerInfo(@Body timerInfo: TimerInfo): Response<Unit>
}
