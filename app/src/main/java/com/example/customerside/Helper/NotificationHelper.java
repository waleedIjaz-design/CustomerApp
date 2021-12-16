package com.example.customerside.Helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.customerside.R;

public class NotificationHelper extends ContextWrapper {

    private static final String PASHA_CHANNEL_ID = "com.example.customerside";
    private static final String PASHA_CHANNEL_NAME = "PASHA Uber";
            private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannels();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannels() {
        NotificationChannel pashaChannels = new NotificationChannel(PASHA_CHANNEL_ID,
                PASHA_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT);
        pashaChannels.enableLights(true);
        pashaChannels.enableVibration(true);
        pashaChannels.setLightColor(Color.GRAY);
        pashaChannels.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(pashaChannels);
    }

    public NotificationManager getManager() {
        if (manager == null)
            manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        return manager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getUberNotification(String title, String content , PendingIntent contentIntent,
                                                    Uri soundUri)
    {
        return new Notification.Builder(getApplicationContext(),PASHA_CHANNEL_ID)
                .setContentText(content)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_car);

    }
}
