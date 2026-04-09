package com.example.intent_with_compose

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: "Alarm Triggered!"
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        // Here you could start a service, play a sound, or show a notification
    }
}