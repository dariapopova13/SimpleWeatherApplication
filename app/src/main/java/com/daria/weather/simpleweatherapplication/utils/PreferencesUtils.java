package com.daria.weather.simpleweatherapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.daria.weather.simpleweatherapplication.R;

/**
 * Created by Daria Popova on 07.11.17.
 */

public final class PreferencesUtils {

    private static final String LOCATION_KEY = "location";
    private static final String UNITS_KEY = "units";
    private static final String LAT_COORD_KEY = "lat_coord";
    private static final String LON_COORD_KEY = "lon_coord";

    public static String getCity(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String location = sp.getString(LOCATION_KEY,
                context.getString(R.string.default_preferences_location));
        return location;
    }

    public static void setLocation(Context context, String location) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(LOCATION_KEY, location);
        editor.apply();
    }

    public static String getMetric(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String metricUnits = context.getString(R.string.default_preferences_units);
        String units = sp.getString(UNITS_KEY, metricUnits);
        return units;
    }

    public static void setUnits(Context context, String units) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(UNITS_KEY, units);
        editor.apply();
    }

    public static double[] getLocationCoordintes(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        double[] coord = new double[2];
        coord[0] = Double.longBitsToDouble(sp.getLong(LAT_COORD_KEY, Double.doubleToLongBits(0.0)));
        coord[1] = Double.longBitsToDouble(sp.getLong(LON_COORD_KEY, Double.doubleToLongBits(0.0)));
        return coord;
    }

    public static void setLocationCoord(Context context, double lat, double lon) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(LAT_COORD_KEY, Double.doubleToLongBits(lat));
        editor.putLong(LON_COORD_KEY, Double.doubleToLongBits(lon));
        editor.apply();
    }



}
