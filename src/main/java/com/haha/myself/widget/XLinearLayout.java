package com.haha.myself.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * @author xj
 * Created by xj on 2018/4/17.
 */
public class XLinearLayout extends LinearLayout {

    private final static String TAG = "XLinearLayout";

    public XLinearLayout(Context context) {
        super(context);
    }

    public XLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent:MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent:MotionEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent:MotionEvent.ACTION_UP");
                break;
        }
        boolean bool = super.dispatchTouchEvent(ev);
        //Log.d(TAG, "dispatchTouchEvent:"+bool);
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent:MotionEvent.ACTION_DOWN");
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent:MotionEvent.ACTION_MOVE");
                return true;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent:MotionEvent.ACTION_UP");
                return false;
        }
        boolean isIntercept = super.onInterceptTouchEvent(ev);
        Log.d(TAG, "onInterceptTouchEvent:"+isIntercept);
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent:MotionEvent.ACTION_DOWN");
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent:MotionEvent.ACTION_MOVE");
                return false;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent:MotionEvent.ACTION_UP");
                return true;
        }
        boolean isCosume = super.onTouchEvent(ev);
        Log.d(TAG, "onTouchEvent:"+isCosume);
        return isCosume ;
    }
}
