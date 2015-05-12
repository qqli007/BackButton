package com.lz.androidbutton.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.lz.androidbutton.app.ui.component.TouchViewManager;

/**
 * Created by lz on 2015/1/5.
 */

public class TouchWindowService extends Service {

    private static final String TAG = "TouchWindowService";

    private TouchViewManager touchViewManager;

    @Override
    public void onCreate() {
        Log.d("0-0", TAG + "   onCreate");
        touchViewManager = TouchViewManager.getInstance();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("0-0", TAG + "   onStartCommand");
        touchViewManager.showView();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.d("0-0", TAG + "   onDestroy");
        touchViewManager.removeView();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("0-0", TAG + "   onBind");
        return null;
    }


}
