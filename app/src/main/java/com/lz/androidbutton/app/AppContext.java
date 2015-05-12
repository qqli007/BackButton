package com.lz.androidbutton.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by lz on 2015/1/6.
 */


public class AppContext extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }


}
