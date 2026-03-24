package com.shaat.zahraa.data.models

data class Call(
    val callId: String = "",
    val callerId: String = "",
    val callerName: String = "",
    val receiverId: String = "",
    val receiverName: String = "",
    val callType: String = "", // "audio", "video"
    val status: String = "", // "incoming", "outgoing", "missed", "completed", "rejected"
    val startTime: Long = 0,
    val endTime: Long = 0,
    val duration: Long = 0,
    val timestamp: Long = System.currentTimeMillis()
)
