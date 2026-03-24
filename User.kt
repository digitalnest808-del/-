package com.shaat.zahraa.data.models

data class User(
    val uid: String = "",
    val displayName: String = "",
    val phoneNumber: String = "",
    val profileImageUrl: String = "",
    val statusMessage: String = "",
    val isOnline: Boolean = false,
    val lastSeen: Long = 0,
    val isAdmin: Boolean = false,
    val isBanned: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val blockedUsers: List<String> = emptyList()
)
