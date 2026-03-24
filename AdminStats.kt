package com.shaat.zahraa.data.models

data class AdminStats(
    val totalUsers: Int = 0,
    val activeUsersToday: Int = 0,
    val totalMessages: Long = 0,
    val totalCalls: Long = 0,
    val totalGroups: Int = 0,
    val totalStatus: Int = 0,
    val reportsCount: Int = 0,
    val bannedUsers: Int = 0,
    val serverUptime: Long = 0,
    val databaseSize: Long = 0,
    val storageUsed: Long = 0,
    val dailyActiveUsers: List<DailyStats> = listOf()
)

data class DailyStats(
    val date: String,
    val activeUsers: Int,
    val newUsers: Int,
    val messages: Int
)
