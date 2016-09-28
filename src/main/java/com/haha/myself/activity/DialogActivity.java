package com.haha.myself.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.haha.myself.R;
import com.haha.myself.widget.LoadingDialog;

/**
 * 用于研究和学习Dialog 1.不依赖activity的dialog实现方式；2.通过WindowManager实现
 */
public class DialogActivity extends AppCompatActivity {

    private LoadingDialog mDialog;
    private WindowManager wm;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_test1:
                showProgressDialog(R.string.dialog_msg);
                break;
            case R.id.btn_test2:
                showDialogByWindow();
                break;
            default:
                break;
        }
    }

    private void showDialogByWindow() {
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.height = -1;
        params.width = -1;
        params.format = -1;
        params.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        view = LayoutInflater.from(this).inflate(R.layout.loadingdialog,null);
        wm.addView(view,params);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wm.removeView(view);
            }
        });
    }

    private void showProgressDialog(final int dialog_msg) {
        if(mDialog == null){
            mDialog = createLoadingDialog();
        }

        mDialog.setMessage(dialog_msg);
        mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        //finish();
        mDialog.show();
    }

    private LoadingDialog createLoadingDialog() {
        LoadingDialog loadingDialog = new LoadingDialog(getApplication(),R.style.Dialog_Fullscreen);//非Activity Context，使用的是getApplication()
        loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // onProgressCancelled();
            }
        });

        return loadingDialog;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        try {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                wm.removeView(view);
                return true;
            }
        } catch (Exception e) {

        }

        return super.onKeyDown(keyCode, event);
    }


}
