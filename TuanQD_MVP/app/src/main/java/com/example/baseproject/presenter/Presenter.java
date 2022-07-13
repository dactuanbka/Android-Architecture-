package com.example.baseproject.presenter;

import com.example.baseproject.model.AppInfor;

// resolve userAction and resolve changed data to request Update View, request update DATA.

public class Presenter implements Contract.Presenter, Contract.Model.OnFinishedListener {
    private Contract.MainView mainView;
    private Contract.ViewDisplayMaxApp viewDisplayMaxApp;
    private Contract.Model model;

    public Presenter(Contract.MainView mainView, Contract.Model model) {
        this.mainView = mainView;
        this.model = model;
    }

    public Presenter(Contract.ViewDisplayMaxApp viewDisplayMaxApp, Contract.Model model) {
        this.viewDisplayMaxApp = viewDisplayMaxApp;
        this.model = model;
    }

    @Override
    public void onFinished(AppInfor appInfor) {                     // update View

        if (viewDisplayMaxApp != null) {
            viewDisplayMaxApp.setAppName(appInfor.getAppName());
            viewDisplayMaxApp.setAppIcon(appInfor.getAppIcon());
            viewDisplayMaxApp.setAppDuration(appInfor.getAppDuration());
        }
    }

    @Override // user Action from View
    public void onButtonGetMaxDuration(AppInfor appInfor) { // request update View, request update data
        if (mainView != null) {
            mainView.displayMaxAppActivity();
            model.onClickGetMaxApp(this, appInfor);
        }
    }

    // destroy
    @Override
    public void mainOnDestroy() {
        mainView = null;
    }

    @Override
    public void displayMaxAppOnDestroy() {
        viewDisplayMaxApp = null;
    }
}
