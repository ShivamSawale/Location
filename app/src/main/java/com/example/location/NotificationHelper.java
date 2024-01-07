package com.example.location;


//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.ContextWrapper;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.os.Build;
//
//import androidx.annotation.RequiresApi;
//import androidx.core.app.ActivityCompat;
//import androidx.core.app.NotificationCompat;
//import androidx.core.app.NotificationManagerCompat;
//
//import com.example.location.R;
//
//import java.util.Random;
//
//public class NotificationHelper extends ContextWrapper {
//
//    private static final String TAG = "NotificationHelper";
//
//    public NotificationHelper(Context base) {
//        super(base);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createChannels();
//        }
//    }
//
//    private String CHANNEL_NAME = "High priority channel";
//    private String CHANNEL_ID = "com.example.location" + CHANNEL_NAME; // use notification in place of location
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void createChannels() {
//        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
//        notificationChannel.enableLights(true);
//        notificationChannel.enableVibration(true);
//        notificationChannel.setDescription("this is the description of the channel.");
//        notificationChannel.setLightColor(Color.RED);
//        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.createNotificationChannel(notificationChannel);
//    }
//
//    public void sendHighPriorityNotification(String title, String body, Class activityName) {
//
//        Intent intent = new Intent(this, activityName);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 267, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//          .setContentTitle(title)
//            .setContentText(body)
//                .setSmallIcon(R.drawable.baseline_notifications_24)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setStyle(new NotificationCompat.BigTextStyle().setSummaryText("summary").setBigContentTitle(title).bigText(body))
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
//                .build();
//
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//         TODO: Consider calling
//             ActivityCompat#requestPermissions
//           here to request the missing permissions, and then overriding
//              public void onRequestPermissionsResult(int requestCode, String[] permissions,
//           int[] grantResults)
//             to handle the case where the user grants the permission. See the documentation
//             for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        NotificationManagerCompat.from(this).notify(new Random().nextInt(), notification);
//
//
//    }
//
//}

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.location.R;

import java.util.Random;

public class NotificationHelper extends ContextWrapper {

    private static final String TAG = "NotificationHelper";
    private static final String CHANNEL_ID = "com.example.location.HighPriorityChannel";

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannels() {
        NotificationChannel notificationChannel = new NotificationChannel(
                CHANNEL_ID,
                "High priority channel",
                NotificationManager.IMPORTANCE_HIGH
        );
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setDescription("This is the description of the channel.");
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        NotificationManager manager = getSystemService(NotificationManager.class);
        if (manager != null) {
            manager.createNotificationChannel(notificationChannel);
        } else {
            Log.e(TAG, "NotificationManager is null");
        }
    }

    public void sendHighPriorityNotification(String title, String body, Class<?> NotificationHelper) {
        Intent intent = new Intent(this, NotificationHelper);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        NotificationManagerCompat.from(this).notify(new Random().nextInt(), notification);
    }
}
