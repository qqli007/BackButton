package com.lz.androidbutton.app.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lz.androidbutton.app.R;

/**
 * Created by lz on 2015/1/5.
 */


public class ActionPanelView extends FrameLayout {

    private Context context;

    private TextView back_btn, home_btn, recent_btn, nothing_btn, setting_btn;

    public ActionPanelView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public ActionPanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public ActionPanelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.touch_window_view, this, false);
        this.addView(view);

        back_btn = (TextView) view.findViewById(R.id.back_btn);
        home_btn = (TextView) view.findViewById(R.id.home_btn);
        recent_btn = (TextView) view.findViewById(R.id.recent_btn);
        nothing_btn = (TextView) view.findViewById(R.id.nothing_btn);
        setting_btn = (TextView) view.findViewById(R.id.setting_btn);

    }


    public void setClickListener(OnClickListener clickListener) {
        back_btn.setOnClickListener(clickListener);
        home_btn.setOnClickListener(clickListener);
        recent_btn.setOnClickListener(clickListener);
        nothing_btn.setOnClickListener(clickListener);
        setting_btn.setOnClickListener(clickListener);
    }



}
