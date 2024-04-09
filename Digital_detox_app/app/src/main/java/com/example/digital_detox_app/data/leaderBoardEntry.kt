package com.example.digital_detox_app.data

import kotlinx.serialization.Serializable

@Serializable
data class leaderBoardEntry (
    val username: String,
    val position: Int,
    val numberOfSessions: Int,
    val totalDurationOfSessions: Int
)

data class LeaderboardResponse(
    val date: String,
    val leaderboard: List<leaderBoardEntry>
)