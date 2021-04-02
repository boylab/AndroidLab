package com.boylab.projectstruct.ui.moduleA;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.boylab.projectstruct.R;

public class MainActivity extends AppCompatActivity {

    private static final int RequestCode_RequestAllPermissions = 0x01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasAllPermissions()) {
            onPermissionGranted();
            finish();
        } else {
            requestAllPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case RequestCode_RequestAllPermissions:
                if (hasAllPermissions()) {
                    onPermissionGranted();
                }
                finish();
                break;
        }
    }

    private boolean hasAllPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean hasLocationPermission = (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
            boolean hasCameraPermission = (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
            boolean hasStoragePermission = (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
            return hasLocationPermission && hasCameraPermission && hasStoragePermission;
        }
        return true;
    }

    private void requestAllPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String permissions[] = {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
            };
            requestPermissions(permissions, RequestCode_RequestAllPermissions);
        }
    }

    private void onPermissionGranted() {
        checkUpdate();
        syncProgress();
        gotoActivity();
    }

    private void checkUpdate() {

    }

    private void syncProgress() {

    }

    private void gotoActivity() {

    }
}