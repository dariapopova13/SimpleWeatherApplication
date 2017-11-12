package com.daria.weather.simpleweatherapplication.utils;

import com.daria.weather.simpleweatherapplication.R;

/**
 * Created by Daria Popova on 13.11.17.
 */

public final class DataUtils {

    public static String windDirection(float degrees) {
        String direction = "Unknown";
        if (degrees >= 337.5 || degrees < 22.5) {
            direction = "North";
        } else if (degrees >= 22.5 && degrees < 67.5) {
            direction = "Northeast";
        } else if (degrees >= 67.5 && degrees < 112.5) {
            direction = "East";
        } else if (degrees >= 112.5 && degrees < 157.5) {
            direction = "Southeast";
        } else if (degrees >= 157.5 && degrees < 202.5) {
            direction = "South";
        } else if (degrees >= 202.5 && degrees < 247.5) {
            direction = "Southwest";
        } else if (degrees >= 247.5 && degrees < 292.5) {
            direction = "West";
        } else if (degrees >= 292.5 && degrees < 337.5) {
            direction = "Northwest";
        }
        return direction;
    }

    public static int getIcon(int weatherId) {

        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_thunderstorm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_drizzle;
        } else if (weatherId >= 500 && weatherId <= 531) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.ic_fog;
        } else if (weatherId == 800) {
            return R.drawable.ic_sunny;
        } else if (weatherId == 801) {
            return R.drawable.ic_cloud;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.ic_clouds;
        } else if (weatherId >= 900 && weatherId <= 906) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 958 && weatherId <= 962) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 951 && weatherId <= 957) {
            return R.drawable.ic_sunny;
        }

        return R.drawable.ic_sunny;
    }
}
