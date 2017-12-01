package com.daria.weather.simpleweatherapplication.location;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;

import com.daria.weather.simpleweatherapplication.utils.PreferencesUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Daria Popova on 14.11.17.
 */

public class WeatherLocationManager {

    private final Activity activity;
    private final FusedLocationProviderClient client;
    private final PreferencesUtils preferencesUtils;

    public WeatherLocationManager(Activity activity,
                                  FusedLocationProviderClient client,
                                  PreferencesUtils preferencesUtils) {
        this.activity = activity;
        this.client = client;
        this.preferencesUtils = preferencesUtils;
    }


    @SuppressWarnings("MissingPermission")
    public void getAddress() {
        client.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location == null) {
                            return;
                        }
                        if (!Geocoder.isPresent()) {
                            return;
                        }
                        Geocoder geocoder = new Geocoder(activity, Locale.ENGLISH);
                        try {
                            List<Address> addresses = geocoder.getFromLocation(
                                    location.getLatitude(),
                                    location.getLongitude(),
                                    1);
                            preferencesUtils.setAddress(addresses.get(0));
                            if (activity instanceof Callback) {
                                ((Callback) activity).onSuccess();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (activity instanceof Callback) {
                    ((Callback) activity).onFuilure();
                }
            }
        });
    }

    public interface Callback {

        void onSuccess();

        void onFuilure();
    }
}
