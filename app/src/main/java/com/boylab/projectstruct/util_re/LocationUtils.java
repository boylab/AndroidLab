package com.boylab.projectstruct.util_re;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class LocationUtils {

    public static boolean hasLocationPermission(Activity activity) {
        boolean hasPermission = false;
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity != null) {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    hasPermission = true;
                }
            }
        } else {
            hasPermission = true;
        }
        return hasPermission;
    }

    public static void requestLocationPermission(Activity activity, int requestCode) {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

}
