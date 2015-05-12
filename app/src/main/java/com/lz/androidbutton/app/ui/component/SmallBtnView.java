package com.lz.androidbutton.app.ui.component;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lz.androidbutton.app.R;

/**
 * Created by lz on 2015/1/5.
 */
public class SmallBtnView extends FrameLayout {

    private static final String TAG = "SmallBtnView";

    private Context mContext;

    private GestureDetectorCompat mDetector;
    private SmallViewOnTouchListener mOnTouchListener;

    private TextView ok_btn;

    public SmallBtnView(Context context) {
        super(context);
        this.mContext = context;
        initView();

    }

    public SmallBtnView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public SmallBtnView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
    }


    private void initView(){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.small_btn_layout, null);
        this.addView(view);

        mDetector = new GestureDetectorCompat(mContext, new SmallViewOnGestureListener());

        ok_btn = (TextView) view.findViewById(R.id.ok_btn);
        ok_btn.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }


    class SmallViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("0-0", "----------" + TAG + "  onSingleTapConfirmed");
            if (mOnTouchListener != null) {
                mOnTouchListener.onClickConfirmed();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("0-0", "----------" + TAG + "   onScroll");
            if (mOnTouchListener != null) {
//                mOnTouchListener.onScroll(distanceX, distanceY);
                return true;
            } else {
                return false;
            }

        }
    }

    public void setmOnTouchListener(SmallViewOnTouchListener mOnTouchListener) {
        this.mOnTouchListener = mOnTouchListener;
    }

    public interface SmallViewOnTouchListener{

        public void onClickConfirmed();

        public void onScroll(float x, float y);

    }

}
