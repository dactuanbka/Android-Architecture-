package com.example.baseproject.viewModel;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.baseproject.model.AppInfor;
import com.example.baseproject.view.DisplayMaxAppActivity;
import com.example.baseproject.view.IShareData;
import com.example.baseproject.BR;

import java.util.ArrayList;
import java.util.List;

public class AppInfoViewModel extends BaseObservable {
    private AppInfor mAppInfo;
    private Context mContext;
    static AppInfor maxAppInfor;
    private IShareData iShareData;
    List<AppInfor> listAppInfo = new ArrayList<>();
    int maxDuration = 0, maxDurationPosition = 0;

    public AppInfoViewModel(Context context) {
        mAppInfo = new AppInfor();
        mContext = context;
    }

    public AppInfoViewModel(Context context, IShareData iShareData) {
        mAppInfo = new AppInfor();
        mContext = context;
        this.iShareData = iShareData;
        setMaxAppInfor();
    }

    @Bindable
    public String getAppName() {
        return mAppInfo.getAppName();
    }

    @Bindable
    public Drawable getAppIcon() {
        return mAppInfo.getAppIcon();
    }

    @Bindable
    public String getAppDuration() {
        return mAppInfo.getAppDuration();
    }

    public void setAppName(AppInfor appInfor) {
        mAppInfo.setAppName(appInfor.getAppName());
        notifyPropertyChanged(BR.appName);
    }

    public void setAppIcon(AppInfor appInfor) {
        mAppInfo.setAppIcon(appInfor.getAppIcon());
        notifyPropertyChanged(BR.appIcon);

    }

    public void setAppDuration(AppInfor appInfor) {
        mAppInfo.setAppDuration(appInfor.getAppDuration());
        notifyPropertyChanged(BR.appDuration);
    }

    public void buttonGetMaxApp(List<AppInfor> listAppInfo) {
        this.listAppInfo = listAppInfo;
        for (int i = 0; i < listAppInfo.size(); i++) {
            if (maxDuration < Integer.parseInt(listAppInfo.get(i).getAppDuration())) {
                maxDuration = Integer.parseInt(listAppInfo.get(i).getAppDuration());
                maxDurationPosition = i;
            }
        }

    }
    public void buttonGetMaxApp() {
        maxAppInfor = listAppInfo.get(maxDurationPosition);
    }

    public void setMaxAppInfor() {
        iShareData.shareData(maxAppInfor);
    }

}
