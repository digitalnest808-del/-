package com.shaat.zahraa.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class CallService : Service() {
    
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // TODO: Handle incoming calls
        return START_STICKY
    }
}
