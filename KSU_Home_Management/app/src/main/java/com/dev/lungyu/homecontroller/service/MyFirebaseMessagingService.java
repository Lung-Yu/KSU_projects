package com.dev.lungyu.homecontroller.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.dev.lungyu.homecontroller.MainActivity;
import com.dev.lungyu.homecontroller.notification.NotificationTools;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by lungyu on 2/22/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e("message", "onMessageReceived");

        String message_body = remoteMessage.getNotification().getBody();
        String message_title = remoteMessage.getNotification().getTitle();
        Log.e("message", message_body);

        NotificationTools notic = new NotificationTools(getApplicationContext());
        notic.sendNotification(message_body);
    }
}