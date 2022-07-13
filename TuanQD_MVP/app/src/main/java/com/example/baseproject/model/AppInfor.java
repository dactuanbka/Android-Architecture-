package com.example.baseproject.model;

import android.graphics.drawable.Drawable;

public class AppInfor {
    private String AppName;
    private String Duration;
    private Drawable AppIcon;

    public AppInfor(String mAppName, String mDuration, Drawable mAppIcon) {
        this.AppName = mAppName;
        this.Duration = mDuration;
        this.AppIcon = mAppIcon;
    }

    public AppInfor() {
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        this.AppName = appName;
    }

    public String getAppDuration() {
        return Duration;
    }

    public void setAppDuration(String duration) {
        this.Duration = duration;
    }

    public Drawable getAppIcon() {
        return AppIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.AppIcon = appIcon;
    }
}
