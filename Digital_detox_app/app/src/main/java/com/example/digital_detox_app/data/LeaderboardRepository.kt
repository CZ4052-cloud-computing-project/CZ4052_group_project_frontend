package com.example.digital_detox_app.data

import com.example.digital_detox_app.network.DigitalDetoxApiService
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface DigitalDetoxLeaderboardRepository {
    suspend fun getLeaderboardInfo(): LeaderBoardResponse
}

class NetworkDigitalDetoxLeaderboardRepository(
    private val leaderboardAPIservice: DigitalDetoxApiService
): DigitalDetoxLeaderboardRepository {
    override suspend fun getLeaderboardInfo(): LeaderBoardResponse {
        // TODO: resolve Call requires API level 26 (current min is 24): java.time.LocalDate#now issue
        val date = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        return leaderboardAPIservice.getLeaderBoardInfo(date)
    }
}