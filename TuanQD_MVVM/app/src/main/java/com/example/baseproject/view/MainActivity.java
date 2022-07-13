package com.example.baseproject.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.baseproject.adapter.ListAppAdapter;
import com.example.baseproject.model.AppInfor;
import com.example.baseproject.thread.FinishedQuery;
import com.example.baseproject.util.ImageUtil;
import com.example.baseproject.viewModel.AppInfoViewModel;
import com.example.baseproject.R;
import com.example.baseproject.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    List<UsageStats> listUsageStats;
    List<AppInfor> listAppInfor;
    AppInfor appInfor;
    ActivityMainBinding binding;
    FinishedQuery finishedQuery = new FinishedQuery();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(new AppInfoViewModel(MainActivity.this));
        binding.executePendingBindings();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TuanQD_MVVM");
        listUsageStats = new ArrayList<>();
        listAppInfor = new ArrayList<>();

        Toast.makeText(this, " Please Allow usage access ", Toast.LENGTH_LONG).show();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.PACKAGE_USAGE_STATS)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.PACKAGE_USAGE_STATS}, 0);
                Log.i("NO PERMISSION", "");
                Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            Log.i("LOG IN TO PERMISSION", "");

            ThreadGetUsageStats threadGetUsageStats = new ThreadGetUsageStats();

            threadGetUsageStats.setName("Query Data Usages Thread");
            threadGetUsageStats.start();
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            binding.recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,
                    1));
            // Waitting for Query Data Usages Thread
            try {
                finishedQuery.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // finish Query
            Log.i("Query", "DONE");
            binding.recyclerView.setAdapter(new ListAppAdapter(listAppInfor));
            binding.recyclerView.setHasFixedSize(true);
            binding.btnGetMaxDuration.setOnClickListener(MainActivity.this);
        }
    }
    public String getListAppUsage(Context context) {
        UsageStatsManager manager = (UsageStatsManager) context.
                getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        listUsageStats = manager.queryUsageStats(UsageStatsManager.INTERVAL_BEST,
                time - 24 * 3600 * 1000, time);
        return null;
    }
    private void changeUsageStatsToAppInfor() {
        for (UsageStats usageState : listUsageStats) {
            // make listAppInfor
            Drawable appIcon = new ImageUtil().getAppIconByPackageName(this,
                    usageState.getPackageName());
            appInfor = new AppInfor(getAppName(this, usageState.getPackageName()),
                    String.valueOf((usageState.getLastTimeStamp()
                            - usageState.getFirstTimeStamp()) / 1000), appIcon);
            listAppInfor.add(appInfor);
        }
    }

    private String getAppName(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
        }
        return (String) (applicationInfo != null ?
                packageManager.getApplicationLabel(applicationInfo) : "Unknown");
    }
// return maxApp
    @Override
    public void onClick(View v) {
        binding.getViewModel().buttonGetMaxApp(listAppInfor);
        binding.getViewModel().buttonGetMaxApp();
        Intent intent= new Intent(MainActivity.this,DisplayMaxAppActivity.class);
        startActivity(intent);
    }

    public class ThreadGetUsageStats extends Thread {
        @Override
        public void run() {
            super.run();
            getListAppUsage(MainActivity.this);
            changeUsageStatsToAppInfor();
            Log.i("THREAD", "" + Thread.currentThread().getName());
            finishedQuery.confirm();
        }
    }

}