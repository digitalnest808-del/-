package com.shaat.zahraa.data.models

data class Report(
    val reportId: String = "",
    val reportedUserId: String = "",
    val reportedByUserId: String = "",
    val reason: String = "",
    val description: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val status: String = "", // "pending", "reviewed", "resolved", "dismissed"
    val adminNotes: String = ""
)
