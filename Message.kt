package com.shaat.zahraa.data.models

data class Message(
    val messageId: String = "",
    val chatId: String = "",
    val senderId: String = "",
    val senderName: String = "",
    val content: String = "",
    val mediaUrl: String = "",
    val mediaType: String = "", // "text", "image", "video", "audio"
    val timestamp: Long = System.currentTimeMillis(),
    val isRead: Boolean = false,
    val readBy: List<String> = emptyList(),
    val editedAt: Long = 0,
    val isDeleted: Boolean = false
)
