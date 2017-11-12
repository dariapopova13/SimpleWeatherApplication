package com.daria.weather.simpleweatherapplication.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.daria.weather.simpleweatherapplication.ui.MainActivity;

/**
 * Created by Daria Popova on 07.11.17.
 */

public class NotificationHelper {

    private Context context;
    public static final String CHANNEL_ID = "1";
    private static final int INTENT_CODE = 1;

    public NotificationHelper(Context context) {
        this.context = context;
    }

    public NotificationCompat.Builder getNotificationBuilder() {

//        PendingIntent intent = PendingIntent.geAc
//        NotificationCompat.Action reloadWeatherAction = new NotificationCompat.Action(
//                android.R.drawable.stat_notify_sync, R.string.sync_action,
//        );
//
        PendingIntent intent = PendingIntent.getActivity(context, INTENT_CODE, new Intent(context, MainActivity.class),
                0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_wb_sunny_black_24dp)
                .setContentText("Sunny")
                .setSubText("good weather")
                .setContentTitle("Title")
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(intent);

        return builder;
    }
}
