package com.haha.myself.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author xj
 * Created by xj on 2018/4/17.
 */
public class XButton extends android.support.v7.widget.AppCompatButton {

    private final static String TAG = "XButton";

    public XButton(Context context) {
        super(context);
    }

    public XButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //getParent().requestDisallowInterceptTouchEvent(true);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent:MotionEvent.ACTION_DOWN");
                //return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent:MotionEvent.ACTION_MOVE");
                //return false;
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent:MotionEvent.ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent:MotionEvent.ACTION_CANCEL");
                break;
        }
        boolean bool = super.dispatchTouchEvent(ev);
        Log.d(TAG, "dispatchTouchEvent:"+bool);
        return bool;
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
        return super.onTouchEvent(ev);
    }
}
