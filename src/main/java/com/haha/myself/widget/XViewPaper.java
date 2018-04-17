package com.haha.myself.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class XViewPaper extends ViewPager {

    private final int mTouchSlop;
    private float mLastMotionX;
    private float mLastMotionY;
    boolean isBeingDrag = false;

    public XViewPaper(Context context) {
        super(context);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
       // mGestureDetector = new GestureDetector(context, new ViewPagerGetector());
    }

    public XViewPaper(Context context, AttributeSet attrs) {
        super(context, attrs);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
       // mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mLastMotionX = ev.getX();
//                mLastMotionY = ev.getY();
//                //Logcat.d("XJ", "Down:mLastX:" + mLastMotionX);
//                //Logcat.d("XJ", "Down:mLastMotionY :" + mLastMotionY);
//                //Logcat.d("XJ", "mTouchSlop :" + mTouchSlop);
//                return true;
//                //break;
//            case MotionEvent.ACTION_MOVE:
//                float x = ev.getX();
//                float y = ev.getY();
//                float dx = Math.abs(mLastMotionX - x);
//                float dy = Math.abs(mLastMotionY - y);
//                //Log.d("XJ", "Move:x:" + x);
//                //Log.d("XJ", "Move:y :" + y);
//                //Log.d("XJ", "Move:dx :" + dx);
//                if (dx > mTouchSlop) {
//                    super.onInterceptTouchEvent(ev);
//                    return true;
//                }
//
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }


        //boolean isTnTercept =  super.onInterceptTouchEvent(ev);
       // boolean isScroll = mGestureDetector.onTouchEvent(ev);

       // Log.d("XJ","isTnTercept:"+isTnTercept);

        //Log.d("XJ","isScroll:"+isScroll);

        return false/*&& isScroll*/;
        //return false;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        super.onTouchEvent(ev);
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mLastMotionX = ev.getX();
//                mLastMotionY = ev.getY();
//                isBeingDrag = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float x = ev.getX();
//                float y = ev.getY();
//                float dx = Math.abs(mLastMotionX - x);
//                float dy = Math.abs(mLastMotionY - y);
//               // Log.d("XJ", "onTouch Move:x:" + x);
//                if (dx > mTouchSlop) {
//                   isBeingDrag = true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                isBeingDrag = false;
//                break;
//        }

         //boolean onTouchEvent = super.onTouchEvent(ev);
        //boolean isScroll = mGestureDetector.onTouchEvent(ev);

       // Log.d("XJ","onTouchEvent(ev):"+onTouchEvent);

       // Log.d("XJ","onTouchEvent(ev):isScrool"+isScroll);

        //return true ;/*&& isScroll;*/
        //return mGestureDetector.onTouchEvent(ev);

        return super.onTouchEvent(ev);
    }

    // Return false if we're scrolling in the x direction
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("XJ","distanceX:"+distanceX+",mTouchSlop:"+mTouchSlop);
            if(mTouchSlop < Math.abs(distanceX))
                return true;
            return false;
        }
    }

    class ViewPagerGetector implements GestureDetector.OnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("XJ","onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("XJ","onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("XJ","onScroll");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("XJ","onFling");
            return false;
        }
    }
}
