package com.boylab.projectstruct.util;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import com.github.shenyuanqing.zxingsimplify.zxing.easyuse.EasyUse;

public class ScanUtils {

    public static boolean hasCameraPermission(Activity activity) {
        boolean hasPermission = false;
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity != null) {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    hasPermission = true;
                }
            }
        } else {
            hasPermission = true;
        }
        return hasPermission;
    }

    public static void requestCameraPermission(Activity activity, int requestCode) {
        String[] permissions = new String[]{
                Manifest.permission.CAMERA
        };
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static void startScan(Activity activity, int requestCode) {
        EasyUse.StartScanActivity(activity, requestCode);
    }

    public static String getScanResult(int resultCode, Intent data) {
        return EasyUse.GetScanActivityResult(resultCode, data);
    }

}
