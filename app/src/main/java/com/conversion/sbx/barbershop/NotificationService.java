package com.conversion.sbx.barbershop;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }

    public void  showNotification(String title, String message){
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.lic_logo);
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        notificationIntent.putExtra(MainActivity.NOTIFICATIONEVENT, "EventMenu");
        PendingIntent pending=PendingIntent.getActivity(getApplicationContext(), 0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Event")
                .setContentIntent(pending)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.lic_logo)
                .setLargeIcon(icon)
                .setAutoCancel(true)
                .setContentText(message);



        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999, builder.build());

    }
}
