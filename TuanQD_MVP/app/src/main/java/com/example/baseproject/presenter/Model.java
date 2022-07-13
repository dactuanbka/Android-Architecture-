package com.example.baseproject.presenter;

import com.example.baseproject.model.AppInfor;

public class Model implements Contract.Model {
    @Override
    public void onClickGetMaxApp(OnFinishedListener onFinishedListener, AppInfor appInfor) {
        onFinishedListener.onFinished(appInfor);
    }
    // set data to interface model(update to data)


}
