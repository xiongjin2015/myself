package com.haha.myself.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * @author xj
 * Created by xj on 2018/4/17.
 */
public class XTextView extends TextView {
    public XTextView(Context context) {
        super(context);
    }

    public XTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("XJ", "child:dispatchTouchEvent:MotionEvent.ACTION_DOWN");
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.d("XJ", "child:dispatchTouchEvent:MotionEvent.ACTION_MOVE");
                return false;
            case MotionEvent.ACTION_UP:
                Log.d("XJ", "child:dispatchTouchEvent:MotionEvent.ACTION_UP");
                return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("XJ", "child:onTouchEvent:MotionEvent.ACTION_DOWN");
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.d("XJ", "child:onTouchEvent:MotionEvent.ACTION_MOVE");
                return true;
            case MotionEvent.ACTION_UP:
                Log.d("XJ", "child:onTouchEvent:MotionEvent.ACTION_UP");
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
