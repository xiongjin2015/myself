package com.haha.myself.activity;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.haha.myself.R;

/**
 *
 * handler study
 * 1.如何让handler运行在子线程---与子线程相关联
 * 2.don‘t forget handlerThread.start();
 *
 */
public class HandlerActivity extends AppCompatActivity implements Handler.Callback{

    private Handler mHandler;
    private final static int MSG_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

    }

    public void onClick(View view){
        HandlerThread handlerThread = new HandlerThread("handlerTest",android.os.Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start(); //if not start,will has NullPointerException
        mHandler = new Handler(handlerThread.getLooper(),this); //传入的是子线程的looper，这handler运行在子线程，若传入Looper.getMainLooper()这传入的是主线程的Looper；
        //mHandler = new Handler(this.getMainLooper(),this);
        //mHandler = new Handler(Looper.getMainLooper(),this);
        //mHandler = new Handler(handlerThread.getLooper(),new CallbackImpl());
        mHandler.sendEmptyMessage(MSG_ID);
    }

    /**
     * see this method is invoked in non-UI Thread or Main Thread
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case MSG_ID:
                handle();
                break;

            default:
                break;
        }
        return false;
    }

    private void handle() {
        Log.i("XJ", "Current Thread is:" + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class CallbackImpl implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_ID:
                    handle();
                    break;

                default:
                    break;
            }
            return false;
        }

    }
}
