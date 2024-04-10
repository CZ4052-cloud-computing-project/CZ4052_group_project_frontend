package com.example.digital_detox_app.data

data class LeaderBoardEntry (
    val position: Int,
    val username: String,
    val numberOfSessions: Int,
    val totalDurationOfSessions: Int
)

data class LeaderBoardResponse(
    val date: String,
    val leaderboard: List<LeaderBoardEntry>
)