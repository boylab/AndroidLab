package com.boylab.projectstruct.util;

import android.content.Context;
import android.net.Uri;

import java.io.InputStream;

public class UriUtils {

    public static byte[] ReadFromFile(Context context, Uri fileUri) {
        byte[] data = null;
        try {
            InputStream in = context.getContentResolver().openInputStream(fileUri);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
        return data;
    }

    public static String GetUriName(Uri fileUri) {
        String name = null;
        try {
            String fullName = Uri.decode(fileUri.toString());
            name = fullName.substring(fullName.lastIndexOf('/') + 1);
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
        return name;
    }

}
