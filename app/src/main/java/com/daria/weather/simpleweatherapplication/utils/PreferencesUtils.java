package com.daria.weather.simpleweatherapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.preference.PreferenceManager;

import com.daria.weather.simpleweatherapplication.R;

import dagger.Reusable;

/**
 * Created by Daria Popova on 07.11.17.
 */
@Reusable
public final class PreferencesUtils {


    private final Context context;
    private final SharedPreferences sp;

    public PreferencesUtils(Context context, SharedPreferences sp) {
        this.context = context;
        this.sp = sp;
    }

    public String getFullNameLocation() {
        return sp.getString(getKey(R.string.full_name_location_key), "");
    }

    public String getCity() {
        String key = getKey(R.string.pref_location_key);
        String location = sp.getString(key,
                context.getString(R.string.default_preferences_location));
        return location;
    }

    public String getMetric() {
        String key = getKey(R.string.pref_units_key);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String metricUnits = context.getString(R.string.default_preferences_units);
        String units = sp.getString(key, metricUnits);
        return units;
    }

    public double[] getLocationCoordintes() {
        String latKey = getKey(R.string.pref_lat_coord_key);
        String lonKey = getKey(R.string.pref_lon_coord_key);
        double[] coord = new double[2];
        coord[0] = Double.longBitsToDouble(sp.getLong(latKey, Double.doubleToLongBits(0.0)));
        coord[1] = Double.longBitsToDouble(sp.getLong(lonKey, Double.doubleToLongBits(0.0)));
        return coord;
    }

    public void setAddress(Address address) {
        SharedPreferences.Editor editor = sp.edit();
        setCity(formCityWithCountryName(address), editor);
        setLocationCoord(address.getLatitude(), address.getLongitude(), editor);
        setFullName(address, editor);
        editor.apply();
    }

    private void setFullName(Address address, SharedPreferences.Editor editor) {
        String name = address.getLocality()
                .concat(", ")
                .concat(address.getAdminArea())
                .concat(", ")
                .concat(address.getCountryName());
        String key = getKey(R.string.full_name_location_key);
        editor.putString(key, name);
    }

    private String formCityWithCountryName(Address address) {
        return address.getLocality()
                .replaceAll("'", "")
                .concat(",")
                .concat(address.getCountryCode());
    }

    private void setCity(String location, SharedPreferences.Editor editor) {
        String key = getKey(R.string.pref_location_key);
        editor.putString(key, location);
    }

    private void setLocationCoord(double lat, double lon, SharedPreferences.Editor editor) {
        String latKey = getKey(R.string.pref_lat_coord_key);
        String lonKey = getKey(R.string.pref_lon_coord_key);

        editor.putLong(latKey, Double.doubleToLongBits(lat));
        editor.putLong(lonKey, Double.doubleToLongBits(lon));

    }

    private void setUnits(String units) {
        String key = getKey(R.string.pref_units_key);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, units);
        editor.apply();
    }


    private String getKey(int id) {
        return context.getString(id);
    }
}
