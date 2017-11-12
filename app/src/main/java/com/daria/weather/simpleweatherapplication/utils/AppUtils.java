package com.daria.weather.simpleweatherapplication.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Daria Popova on 28.09.17.
 */

public class AppUtils {

    private static final String[] PERMISION_STORAGE = {
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final int REQUEST_ACCESS_COARSE_LOCATION = 1;

    public static void verifyPermissions(Activity activity) {
        int permision =
                ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permision != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    PERMISION_STORAGE,
                    REQUEST_ACCESS_COARSE_LOCATION);
        }
    }


    public static String getWeekday(Date date) {

        String result = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.get(Calendar.DAY_OF_WEEK);
        DayOfWeek.of(calendar.get(Calendar.DAY_OF_WEEK)).name();
        return result;
    }

}
