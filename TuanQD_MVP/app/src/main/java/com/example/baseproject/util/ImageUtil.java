package com.example.baseproject.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.baseproject.R;

public class ImageUtil {
    public Drawable getAppIconByPackageName(Context context, String packageName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationIcon(packageName); // lấy ra icon ứng dụng với package name
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return context.getDrawable(R.drawable.ic_launcher_foreground);
    }
}
