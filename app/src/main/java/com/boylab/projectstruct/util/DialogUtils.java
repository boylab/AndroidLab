package com.boylab.projectstruct.util;

import android.app.AlertDialog;
import android.content.Context;

public class DialogUtils {

    public static void ShowDialogMessage(Context context, String title, String message) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .create();
        dialog.show();
    }

}
