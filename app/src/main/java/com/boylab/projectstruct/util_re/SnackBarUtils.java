package com.boylab.projectstruct.util_re;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

public class SnackBarUtils {

    public static void showMessage(Activity activity, String message) {
        try {
            // activity.getWindow().getDecorView() 有可能报异常
            // 如果异常，就用Toast提示
            Snackbar.make(activity.getWindow().getDecorView(), message, Toast.LENGTH_SHORT).show();
            //TopSnackbar.make(activity.getWindow().getDecorView(), message, BaseTransientBottomBar.LENGTH_SHORT).show();
        } catch (Throwable tr) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showMessage(Activity activity, @StringRes int resId) {
        try {
            // activity.getWindow().getDecorView() 有可能报异常
            // 如果异常，就用Toast提示
            Snackbar.make(activity.getWindow().getDecorView(), resId, Toast.LENGTH_SHORT).show();
            //TopSnackbar.make(activity.getWindow().getDecorView(), resId, BaseTransientBottomBar.LENGTH_SHORT).show();
        } catch (Throwable tr) {
            Toast.makeText(activity, resId, Toast.LENGTH_SHORT).show();
        }
    }

}
