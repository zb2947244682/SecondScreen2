package com.simplelife.ss

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.core.app.NotificationCompat

object NotificationUtils {

    private var NOTIFICATION_ID = 1
    private var CHANNEL_ID = "reback"
    private var CHANNEL_NAME = ""

    fun isNotificationEnabled(context: Context): Boolean {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return notificationManager.areNotificationsEnabled()
    }

    fun openNotificationSettings(context: Context) {
        val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        context.startActivity(intent)
    }

    fun showNotification(context: Context, activity: Activity) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 创建一个Intent，指定要跳转的Activity
        val intent = Intent(context, activity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE)

        val title = context.getString(R.string.s1)
        val content = context.getString(R.string.s2)

        // 创建通知
        val builder =
            NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.ic_cast)
                .setContentTitle(title).setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent)
                .setOngoing(true) // 使通知变为常驻通知
                .setAutoCancel(false) // 设置为不自动取消

        // 显示通知
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    fun createNotificationChannel(context: Context) {
        CHANNEL_NAME = context.getString(R.string.s1)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
            description = "回到应用"
        }
        // 注册通知渠道
        notificationManager.createNotificationChannel(channel)
    }
}
