// NotificationUtils.kt
package com.example.flashcardapp.utils

import ReminderWorker
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.flashcardapp.MainActivity
import java.util.concurrent.TimeUnit

fun createNotificationChannel(context: Context) {
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Flashcard App Notifications"
        val descriptionText = "Channel for Flashcard App Reminders"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("flashcard_channel", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun setupReminder(context: Context) {
    createNotificationChannel(context)

    val reminderRequest = PeriodicWorkRequestBuilder<ReminderWorker>(1, TimeUnit.DAYS)
        .build()

    WorkManager.getInstance(context).enqueue(reminderRequest)
}
