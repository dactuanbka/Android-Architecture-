package com.example.baseproject.presenter;

import android.graphics.drawable.Drawable;

import com.example.baseproject.model.AppInfor;

import java.util.List;

public interface Contract {
    interface ViewDisplayMaxApp {
        void setAppName(String appName);

        void setAppDuration(String appDuration);

        void setAppIcon(Drawable appIcon);
        void displayedMaxAppActivity();
    }

    interface MainView {
        void displayMaxAppActivity();

    }
    interface Model {
        interface OnFinishedListener {
            // function to be called
            // once the Handler of Model class
            // completes its execution
            void onFinished(AppInfor appInfor);
        }

        void onClickGetMaxApp(Contract.Model.OnFinishedListener onFinishedListener,AppInfor appInfor);
    }

    interface Presenter {
        void onButtonGetMaxDuration(AppInfor appInfor);

        void mainOnDestroy();
        void displayMaxAppOnDestroy();
    }
}
