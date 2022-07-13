package com.example.baseproject.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import com.example.baseproject.model.AppInfor;
import com.example.baseproject.viewModel.AppInfoViewModel;
import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityDisplayMaxAppBinding;

public class DisplayMaxAppActivity extends AppCompatActivity implements IShareData{
    ActivityDisplayMaxAppBinding maxAppActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maxAppActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_display_max_app);
        maxAppActivityBinding.setViewModel(new AppInfoViewModel(this, this));
        maxAppActivityBinding.executePendingBindings();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Max Duration App");
    }
    @Override
    public void shareData(AppInfor appInfor) {
        if(appInfor!=null) {
            maxAppActivityBinding.txtMaxAppName.setText(appInfor.getAppName());
            maxAppActivityBinding.txtMaxAppDuration.setText(appInfor.getAppDuration() + " s");
            maxAppActivityBinding.imgMaxAppIcon.setImageDrawable(appInfor.getAppIcon());
        }
    }
}