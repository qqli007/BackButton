package com.lz.androidbutton.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.lz.androidbutton.app.R;
import com.lz.androidbutton.app.TouchWindowService;

public class MainActivity extends Activity {

    private TextView start_service_btn, stop_service_btn, enable_acc;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    private void initView() {
        start_service_btn = (TextView) findViewById(R.id.start_service_btn);
        stop_service_btn = (TextView) findViewById(R.id.stop_service_btn);
        enable_acc = (TextView) findViewById(R.id.enable_acc);

        start_service_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTaskService();
            }
        });

        stop_service_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTaskService();
            }
        });

        enable_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void startTaskService() {
        Log.d("0-0", "----------startTaskService");
        Intent to = new Intent(this, TouchWindowService.class);
        startService(to);
    }

    private void stopTaskService(){
        Log.d("0-0", "----------stopTaskService");
        Intent to = new Intent(this, TouchWindowService.class);
        stopService(to);
    }

}
