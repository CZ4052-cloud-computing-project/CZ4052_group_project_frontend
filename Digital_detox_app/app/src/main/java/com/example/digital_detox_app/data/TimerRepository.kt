package com.example.digital_detox_app.data

import com.example.digital_detox_app.network.DigitalDetoxApiService
import retrofit2.Response

interface DigitalDetoxTimerRepository {
    suspend fun sendTimerInfo(timerInfo: TimerInfo): Response<Unit>
}

class NetworkDigitalDetoxTimerRepository(
    private val digitalDetoxApi: DigitalDetoxApiService
): DigitalDetoxTimerRepository {
    override suspend fun sendTimerInfo(timerInfo: TimerInfo): Response<Unit> {
        return digitalDetoxApi.sendTimerInfo(timerInfo)
    }
}