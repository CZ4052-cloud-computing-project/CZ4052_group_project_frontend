package com.example.digital_detox_app.data

import com.example.digital_detox_app.network.DigitalDetoxApiService

interface DigitalDetoxStatisticsRepository {
    suspend fun getStatistics(
        username: String,
        startDate: String,
        endDate: String,
    ): List<StatisticEntry>
}

class NetworkDigitalDetoxStatisticsRepository(
    private val digitalDetoxApi: DigitalDetoxApiService
): DigitalDetoxStatisticsRepository {
    override suspend fun getStatistics(
        username: String,
        startDate: String,
        endDate: String,
    ): List<StatisticEntry> {
        return digitalDetoxApi.getStatisticsInfo(
            username,
            startDate,
            endDate,
        )
    }
}