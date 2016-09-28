package com.haha.myself.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.haha.myself.R;
import com.haha.myself.view.RoundedImageView;

public class RoundedImageActivity extends AppCompatActivity {

    private RoundedImageView contentImg;
    private int mRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounded_image);
        contentImg = (RoundedImageView) findViewById(R.id.ic_big_image);

        mRadius = getResources().getDimensionPixelSize(
                R.dimen.card_corner_radius);
        Bitmap bitmap =  BitmapFactory.decodeResource(getResources(),android.R.drawable.sym_def_app_icon);
        //Bitmap bitmap =  BitmapFactory.decodeResource(getResources(),R.drawable.card_rounded_image_bg);

        contentImg.setImage(bitmap, mRadius, 12);//最后参数”12”用于指定某个角是不是圆角

    }

}
