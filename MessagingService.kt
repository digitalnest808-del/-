package com.shaat.zahraa.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService() {
    
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // TODO: Handle incoming messages
    }
    
    override fun onNewToken(token: String) {
        // TODO: Handle new FCM token
    }
}
