package com.haha.myself.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haha.myself.R;
import com.haha.myself.widget.XRelativeLayout;

public class EventTestActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private ViewPager viewPager;
    private XRelativeLayout rootView;
    private float mLastMotionX;
    private float mLastMotionY;
    private int mTouchSlop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_test);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        rootView = (XRelativeLayout) findViewById(R.id.frameLayout);
        rootView.setViewPager(viewPager);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                Log.d("XJ", "onTouch");
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
                            Log.d("XJ", "up----viewPager.requestDisallowInterceptTouchEvent(true)");
                            viewPager.requestDisallowInterceptTouchEvent(true);
                        }
                        break;
                }
                return true;
            }
        });

        textView = (TextView)findViewById(R.id.textView);

//        textView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.d("XJ", "onTouch---"+event.getAction()+"---textView");
//                return false;
//            }
//        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("XJ", "onClick---------textView");
            }
        });



        imageView = (ImageView) findViewById(R.id.imageView);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                textView.performClick();
                Log.d("XJ", "onTouch---"+event.getAction()+"---imageView");
                return false;
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.performClick();
                Log.d("XJ", "onClick---------imageView");
            }
        });






        if (viewPager != null) {
            viewPager.setAdapter(new WizardPagerAdapter());
            viewPager.setCurrentItem(1);

//            viewPager.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    Log.d("XJ", "onTouch-------ViewPager");
//                    return false;
//                }
//            });
//
//            viewPager.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("XJ", "onClick-------ViewPager");
//                }
//            });
        }

    }


    class WizardPagerAdapter extends PagerAdapter {
        public View instantiateItem(ViewGroup collection, int position) {
            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.id.left_empty;
                    break;
                case 1:
                    resId = R.id.right_empty;
                    break;
            }
            return findViewById(resId);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == ((View) arg1);
        }
    }
    
}
