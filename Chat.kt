package com.shaat.zahraa.data.models

data class Chat(
    val chatId: String = "",
    val participants: List<String> = emptyList(),
    val lastMessage: String = "",
    val lastMessageTime: Long = 0,
    val lastMessageSender: String = "",
    val unreadCount: Map<String, Int> = emptyMap(),
    val isGroup: Boolean = false,
    val groupName: String = "",
    val groupImageUrl: String = "",
    val groupDescription: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val createdBy: String = "",
    val admins: List<String> = emptyList()
)
