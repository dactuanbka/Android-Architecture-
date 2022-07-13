package com.example.baseproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baseproject.R;
import com.example.baseproject.presenter.Contract;
import com.example.baseproject.presenter.Model;
import com.example.baseproject.presenter.Presenter;

public class DisplayMaxAppActivity extends AppCompatActivity implements Contract.ViewDisplayMaxApp {
    TextView txtMaxAppName, txtMaxAppDuration;
    ImageView imgMaxAppIcon;
    private Contract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_max_app);
        txtMaxAppName = findViewById(R.id.txtMaxAppName);
        txtMaxAppDuration = findViewById(R.id.txtMaxAppDuration);
        imgMaxAppIcon = findViewById(R.id.imgMaxAppIcon);
        presenter = new Presenter(this, new Model());
    }

    @Override
    public void displayedMaxAppActivity() {
    }

    @Override
    public void setAppName(String appName) {
        txtMaxAppName.setText(appName);
    }

    @Override
    public void setAppDuration(String appDuration) {
        txtMaxAppDuration.setText(appDuration);
    }

    @Override
    public void setAppIcon(Drawable appIcon) {
        imgMaxAppIcon.setImageDrawable(appIcon);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.displayMaxAppOnDestroy();
    }
}