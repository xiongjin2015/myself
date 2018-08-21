package com.haha.myself.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haha.myself.R;

/**
 * 自定义动画加载对话框实现
 * 同时实现不依赖activity的系统级弹出dialog
 */
public class LoadingDialog extends Dialog {

    private Context mContext;
    private final TextView mTvMessage;
    private final ImageView mLoadingDots;
    private final AnimationDrawable marqueeDots;
    private String mMsg;

    public LoadingDialog(Context context, int theme) {
        super(context, theme);

        mContext = context;
        
        setContentView(R.layout.loadingdialog);
        
        mTvMessage = (TextView) findViewById(R.id.loading_des);
        mLoadingDots = (ImageView) findViewById(R.id.loading_dots);
        
        marqueeDots = (AnimationDrawable) mLoadingDots.getDrawable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startAnim();
        updateMessage();
    }

    public void setMessage(int resId){
        mMsg = mContext.getString(resId);
        if(isShowing()){
            updateMessage();
        }
    }

    public void setMessage(String msg) {
        mMsg = msg;
        if (isShowing()) {
            updateMessage();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_SEARCH)
            return true;

        return super.onKeyDown(keyCode, event);
    }

    private void updateMessage() {
        mTvMessage.setText(mMsg);
    }

    private void startAnim() {
        mLoadingDots.removeCallbacks(startAnimation);
        mLoadingDots.post(startAnimation);

    }

    private Runnable startAnimation = new Runnable() {
        @Override
        public void run() {
            marqueeDots.stop();
            marqueeDots.start();
        }
    };
}
