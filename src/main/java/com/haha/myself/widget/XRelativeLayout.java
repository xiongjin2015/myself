package com.haha.myself.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

/**
 * @author xj
 *         Created by xj on 17/4/5.
 */

public class XRelativeLayout extends RelativeLayout {
    private final int mTouchSlop;
    private float mLastMotionX;
    private float mLastMotionY;
    private ViewPager viewPager;

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public XRelativeLayout(Context context) {
        super(context);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
    }

    public XRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
    }

    public XRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("XJ", "parent:onInterceptTouchEvent:MotionEvent.ACTION_DOWN");
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.d("XJ", "parent:onInterceptTouchEvent:MotionEvent.ACTION_MOVE");
                return true;
            case MotionEvent.ACTION_UP:
                Log.d("XJ", "parent:onInterceptTouchEvent:MotionEvent.ACTION_UP");
                return true;
        }

        boolean isIntercept = super.onInterceptTouchEvent(ev);
        Log.d("XJ", "parent:onInterceptTouchEvent:"+isIntercept);
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("XJ", "onTouchEvent"+ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = ev.getX();
                mLastMotionY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                float x = ev.getX();
                float y = ev.getY();
                float dx = Math.abs(mLastMotionX - x);
                float dy = Math.abs(mLastMotionY - y);
                if (dx <= mTouchSlop) {
                    Log.d("XJ", "onTouchEvent:up");
                    viewPager.requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return false;
    }
}
