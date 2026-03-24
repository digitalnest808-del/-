package com.shaat.zahraa.data.models

data class Status(
    val statusId: String = "",
    val userId: String = "",
    val mediaUrl: String = "",
    val mediaType: String = "", // "image", "video"
    val caption: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val expiresAt: Long = System.currentTimeMillis() + (24 * 60 * 60 * 1000), // 24 hours
    val viewedBy: List<String> = emptyList(),
    val isPublic: Boolean = true
)
