package com.example.intent_with_compose

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.intent_with_compose.ui.theme.Intent_with_composeTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Intent_with_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AlarmScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AlarmScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var hour by remember { mutableIntStateOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) }
    var minute by remember { mutableIntStateOf(Calendar.getInstance().get(Calendar.MINUTE)) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AndroidView(
            factory = { ctx ->
                TimePicker(ctx).apply {
                    setIs24HourView(true)
                    setOnTimeChangedListener { _, h, m ->
                        hour = h
                        minute = m
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                // If time is in past -> set for next day
                if (before(Calendar.getInstance())) {
                    add(Calendar.DATE, 1)
                }
            }
            if (checkExactAlarmPermission(context)) {
                setAlarm(context, calendar.timeInMillis)
            } else {
                requestExactAlarmPermission(context)
            }
        }) {
            Text("Set Alarm")
        }
    }
}

fun checkExactAlarmPermission(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.canScheduleExactAlarms()
    } else true
}

fun requestExactAlarmPermission(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        try {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Enable permission manually", Toast.LENGTH_LONG).show()
        }
    }
}

@SuppressLint("ScheduleExactAlarm")
fun setAlarm(context: Context, triggerAtMillis: Long) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        triggerAtMillis,
        pendingIntent
    )

    Toast.makeText(context, "Alarm Set Successfully!", Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun AlarmPreview() {
    Intent_with_composeTheme {
        AlarmScreen()
    }
}