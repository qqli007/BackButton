package com.lz.androidbutton.app;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.lz.androidbutton.app.ui.component.TouchViewManager;


/**
 * Created by lz on 2015/5/3.
 */
public class BackKeyService extends AccessibilityService implements TouchViewManager.KeyServiceListener{

    private static final String TAG = "BackKeyService";

    @Override
    protected void onServiceConnected() {
        Log.d("0-0", TAG + "---onServiceConnected");
        super.onServiceConnected();
        TouchViewManager.getInstance().setKeyServiceListener(this);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void backKey() {
        Log.d("0-0", TAG + "---backKey");
        performGlobalAction(GLOBAL_ACTION_BACK);
    }

    @Override
    public void homeKey() {
        Log.d("0-0", TAG + "---homeKey");
        performGlobalAction(GLOBAL_ACTION_HOME);
    }

    @Override
    public void recentKey() {
        Log.d("0-0", TAG + "---recentKey");
        performGlobalAction(GLOBAL_ACTION_RECENTS);
    }

    @Override
    public void settingKey() {
        Log.d("0-0", TAG + "---settingKey");
        performGlobalAction(GLOBAL_ACTION_QUICK_SETTINGS);
    }

    @Override
    public void onInterrupt() {
        Log.d("0-0", TAG + "---onInterrupt");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("0-0", TAG + "---onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d("0-0", TAG + "---onDestroy");
        super.onDestroy();
    }
}
