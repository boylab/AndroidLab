package com.boylab.projectstruct.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

    public static Bitmap getImageFromAssetsFile(Context ctx, String fileName) {
        Bitmap image = null;
        AssetManager am = ctx.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Bitmap scaleImageToWidth(Bitmap bitmap, int w) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        // 缩放图片的尺寸
        float scaleWidth = (float) w / bitmapWidth;
        float scaleHeight = scaleWidth;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, false);
    }

    public static Bitmap scaleImageIfWidthOrHeightOutOfRange(Bitmap bitmap, int maxw, int maxh) {
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        Matrix matrix = new Matrix();
        if ((bitmapWidth > maxw) || (bitmapHeight > maxh)) {
            // 缩放图片的尺寸
            float scaleWidth = (float) maxw / bitmapWidth;
            float scaleHeight = (float) maxh / bitmapHeight;
            float scale = Math.min(scaleWidth, scaleHeight);
            matrix.postScale(scale, scale);
        }

        return Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight, matrix, false);
    }

    public static void selectImage(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, requestCode);
    }

    public static Bitmap getResultImage(Activity activity, Intent data) {
        Bitmap bitmap = null;
        try {
            Uri pictureUri = data.getData();
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), pictureUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
