package com.daria.weather.simpleweatherapplication.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.daria.weather.simpleweatherapplication.utils.PermissionUtils;

/**
 * Created by Daria Popova on 07.11.17.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (!PermissionUtils.checkPermission(this, PermissionUtils.ACCESS_COARSE_LOCATION_PERMISSION)) {
                PermissionUtils.getPermission(this);
            } else {
                startApplication();
            }
        } else {
            startApplication();
        }


    }

    private void startApplication() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.READ_EXTERNAL_STORAGE_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startApplication();
                } else {
                    finish();
                }
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
