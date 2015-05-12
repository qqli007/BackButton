package com.lz.androidbutton.app.ui.component;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import com.lz.androidbutton.app.AppContext;
import com.lz.androidbutton.app.R;

/**
 * Created by lz on 2015/1/6.
 * <p/>
 * DoWhat:
 */

public class TouchViewManager {

    private static final String TAG = "TouchViewManager";

    private static TouchViewManager touchViewManager = null;

    private TouchViewManager() {
        init();
    }

    public static TouchViewManager getInstance() {
        if (touchViewManager == null) {
            synchronized (TouchViewManager.class) {
                if (touchViewManager == null) {
                    touchViewManager = new TouchViewManager();
                }
            }
        }

        return touchViewManager;
    }


    private WindowManager.LayoutParams smallBtnLayoutParams = null;
    private WindowManager.LayoutParams actionPanelLayoutParams = null;
    private WindowManager windowManager = null;
    private SmallBtnView smallBtnView = null;
    private ActionPanelView actionPanelView = null;

    private int currentView = 0;

    private static final int SMALLBTN_SHOWING = 1;
    private static final int ACTIONPANEL_SHOWING = 2;

    private KeyServiceListener keyServiceListener;


    private void init() {
        smallBtnLayoutParams = new WindowManager.LayoutParams();
        actionPanelLayoutParams = new WindowManager.LayoutParams();

        windowManager = (WindowManager) AppContext.appContext.getSystemService(Context.WINDOW_SERVICE);
    }

    public void removeView() {
        if (currentView == SMALLBTN_SHOWING) {
            windowManager.removeView(smallBtnView);
        } else if (currentView == ACTIONPANEL_SHOWING) {
            windowManager.removeView(actionPanelView);
        }

        currentView = 0;
    }

    public void showView() {
        setLayoutParams();
        showSmallBtnView();
    }

    private void setLayoutParams() {
        //smallbtn
        smallBtnLayoutParams.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
        smallBtnLayoutParams.format = PixelFormat.RGBA_8888;
        smallBtnLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        smallBtnLayoutParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        smallBtnLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        smallBtnLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        //actionpanel
        actionPanelLayoutParams.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
        actionPanelLayoutParams.format = PixelFormat.RGBA_8888;
        actionPanelLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        actionPanelLayoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        actionPanelLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        actionPanelLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }

    //显示小按钮
    private void showSmallBtnView() {
        if (smallBtnView == null) {
            createSmallBtnView();
        }
        if (currentView == SMALLBTN_SHOWING) {
            return;
        } else if (currentView == ACTIONPANEL_SHOWING) {
            windowManager.removeView(actionPanelView);
        }

        windowManager.addView(smallBtnView, smallBtnLayoutParams);
        currentView = SMALLBTN_SHOWING;
    }

    private void createSmallBtnView() {
        smallBtnView = new SmallBtnView(AppContext.appContext);
        smallBtnView.setmOnTouchListener(smallViewOnTouchListener);
    }

    //显示操作面板
    private void showActionPanelView() {
        if (actionPanelView == null) {
            createActionPanelView();
        }
        if (currentView == ACTIONPANEL_SHOWING) {
            return;
        } else if (currentView == SMALLBTN_SHOWING) {
            windowManager.removeView(smallBtnView);
        }

        windowManager.addView(actionPanelView, actionPanelLayoutParams);
        currentView = ACTIONPANEL_SHOWING;
    }

    private void createActionPanelView() {
        actionPanelView = new ActionPanelView(AppContext.appContext);
        actionPanelView.setClickListener(actionPanelOnClickListener);
    }


    private SmallBtnView.SmallViewOnTouchListener smallViewOnTouchListener = new SmallBtnView.SmallViewOnTouchListener() {

        @Override
        public void onClickConfirmed() {
            showActionPanelView();
        }

        @Override
        public void onScroll(float x, float y) {
            smallBtnLayoutParams.x = (int) x;
            smallBtnLayoutParams.y = (int) y;
            windowManager.updateViewLayout(smallBtnView, smallBtnLayoutParams);
        }
    };

    private View.OnClickListener actionPanelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            showSmallBtnView();

            int id = v.getId();
            switch (id) {
                case R.id.back_btn:
                    Log.d("0-0", "backEvent");
                    if (keyServiceListener != null) {
                        keyServiceListener.backKey();
                    }
                    break;
                case R.id.home_btn:
                    Log.d("0-0", "homeEvent");
                    if (keyServiceListener != null) {
                        keyServiceListener.homeKey();
                    }
                    break;
                case R.id.recent_btn:
                    Log.d("0-0", "recentEvent");
                    if (keyServiceListener != null) {
                        keyServiceListener.recentKey();
                    }
                    break;
                case R.id.nothing_btn:
                    break;
                case R.id.setting_btn:
                    Log.d("0-0", "settingEvent");
                    if (keyServiceListener != null) {
                        keyServiceListener.settingKey();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public void setKeyServiceListener(KeyServiceListener keyServiceListener) {
        this.keyServiceListener = keyServiceListener;
    }

    public interface KeyServiceListener{
        void backKey();
        void homeKey();
        void recentKey();
        void settingKey();
    }

}
