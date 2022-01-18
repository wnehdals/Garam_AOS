package com.jdm.garam.util

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.jdm.garam.R
import com.jdm.garam.ui.bus.type.BusTypeActivity
import com.jdm.garam.ui.calendar.GaramCalendarActivity
import com.jdm.garam.ui.realestate.RealEstateMainActivity

class GaramFCMService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        sendNotification(p0)
    }

    private fun sendNotification(message: RemoteMessage) {
        val intent = Intent(this, BusTypeActivity::class.java).putExtra(BUS_TYPE_ID, 10)
        var pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        var pendingActionIntent1 = PendingIntent.getActivity(
            this,
            0,
            Intent(this, GaramCalendarActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        var pendingActionIntent2 = PendingIntent.getActivity(
            this,
            0,
            Intent(this, RealEstateMainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        var action1 = NotificationCompat.Action.Builder(0, "일정", pendingActionIntent1).build()
        var action2 = NotificationCompat.Action.Builder(0, "부동산 정보", pendingActionIntent2).build()
        var builder: NotificationCompat.Builder? = null
        val notificationManager = NotificationManagerCompat.from(applicationContext)

        var title = message.data["title"] ?: ""
        var body = message.data["body"] ?: ""
        var bigText = message.data["big_text"] ?: ""
        var deleteId = message.data["deleteId"]?.toInt() ?: -1
        var titles = arrayOf("광고", "강의", "구독")
        var idx: Int = message.data["idx"]?.toInt() ?: 0
        var k = notificationManager.notificationChannels
        var pushChannelIds = arrayOf("1", "2", "3")
        var pushNotiName = arrayOf("광고알림", "강의알림", "구독알림")
        var pushNotiId = arrayOf(1, 2, 3)
        for (i in 0 until titles.size) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                if (notificationManager.getNotificationChannel(pushChannelIds[i]) == null) {

                    var channel = NotificationChannel(
                        pushChannelIds[i], pushNotiName[i], NotificationManager.IMPORTANCE_HIGH
                    )
                    //channel.group = NOTI_CHANNEL_GROUP_ID
                    notificationManager.createNotificationChannel(channel)
                }
            }
        }

        if (deleteId != -1) {
            notificationManager.cancel(deleteId)
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = NotificationCompat.Builder(applicationContext, pushChannelIds[idx])
        } else {
            builder = NotificationCompat.Builder(applicationContext)
        }

        builder!!.setContentTitle(titles[idx])
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_home)
            .setContentIntent(pendingIntent)
            //.setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .addAction(action1)
            .addAction(action2)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setGroup(NOTI_GROUP_NAME)
            .setAutoCancel(true)
        var notification = builder!!.build()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.notify(pushNotiId[idx], notification)
        } else {
            notificationManager.notify(pushNotiId[idx], notification)
        }




    }
}