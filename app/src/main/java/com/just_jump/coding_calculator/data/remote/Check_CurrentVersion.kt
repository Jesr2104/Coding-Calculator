package com.just_jump.coding_calculator.data.remote

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.just_jump.coding_calculator.BuildConfig
import com.just_jump.coding_calculator.R
import com.just_jump.coding_calculator.UpdateVersion

lateinit var notificationManager: NotificationManager
lateinit var notificationChannel: NotificationChannel
lateinit var builder: Notification.Builder

fun currentVersion(context: Context){

    val channelId = "com.just_jump.coding_calculator.data.remote"
    val description = "test notification"
    val valueCurrentVersion: String? = null

    // -----------------------------------------------------------------------------------
    // Remote Config
    // -----------------------------------------------------------------------------------
    val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds= 3600
    }

    val firebaseConfig = Firebase.remoteConfig
    firebaseConfig.setConfigSettingsAsync(configSettings)

    Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val currentVersion = Firebase.remoteConfig.getString("current_version")

            // here you have to execute the action with the value returned by the service

            val versionName = BuildConfig.VERSION_NAME

            if (versionName != currentVersion){

                notificationManager = (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

                val intent = Intent(context, UpdateVersion::class.java)
                val pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notificationChannel = NotificationChannel(
                        channelId,
                        description,
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(true)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder = Notification.Builder(context, channelId)
                        .setContentTitle(context.getString(R.string.Title_notification))
                        .setSmallIcon(R.drawable.ic_update)
                        .setLargeIcon(BitmapFactory.decodeResource(
                                context.resources,
                                R.mipmap.ic_update
                            )
                        )
                        .setContentText(context.getString(R.string.update_massage) + " v$currentVersion")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                }
                else
                {
                    builder = Notification.Builder(context)
                        .setContentTitle(context.getString(R.string.Title_notification))
                        .setSmallIcon(R.drawable.ic_update)
                        .setLargeIcon(BitmapFactory.decodeResource(
                                context.resources,
                                R.mipmap.ic_update
                            )
                        )
                        .setContentText(context.getString(R.string.update_massage) + " v$currentVersion")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                }
                notificationManager.notify(1234, builder.build())
            }
        }
    }
}