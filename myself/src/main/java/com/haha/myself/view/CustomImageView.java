package com.haha.myself.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.haha.myself.R;

/**
 * Created by baidu on 16/3/15.
 */
public class CustomImageView extends View {

    private static final int IMAGE_SCALE_FITXY = 0;

    /**
     * 展示的Image
     */
    private Bitmap mImage;

    /**
     * Image展示的裁剪方式
     */
    private int mImageScale;

    /**
     * 该View上显示的文本域
     */
    private String mText;

    /**
     * 该View上显示的文本的大小
     */
    private float mTextSize;

    /**
     * 该View上显示的文本的颜色
     */
    private int mTextColor;

    private Rect mRect;

    private Paint mPaint;

    private Rect mTextBound;

    /**
     * 该View的宽
     */
    private int mWidth;

    /**
     * 该View的高
     */
    private int mHeight;

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 初始化所有自定义类型
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyleAttr, 0);

        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {

            int attr = a.getIndex(i);

            if (attr == R.styleable.CustomImageView_image) {
                mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));//将resId转换成Bitmap的Api

            } else if (attr == R.styleable.CustomImageView_imageScaleType) {
                mImageScale = a.getInt(attr, 0);

            } else if (attr == R.styleable.CustomImageView_titleText) {
                mText = a.getString(attr);

            } else if (attr == R.styleable.CustomImageView_titleTextColor) {
                mTextColor = a.getColor(attr, Color.BLACK);

            } else if (attr == R.styleable.CustomImageView_titleTextSize) {
                mTextSize = a.getDimension(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

            } else {
            }

            a.recycle();

            mRect = new Rect(); //该View显示的范围/边界
            mPaint = new Paint(); //相当于画笔
            mTextBound = new Rect(); //该View里的文本域显示的范围/边界

            mPaint.setTextSize(mTextSize); //设置画笔画文本的大小
            mPaint.getTextBounds(mText, 0, mText.length(), mTextBound); //获取和测量文本所占的大小

        }
    }

    /**
     * 对View进行测量
     * 父View负责子View的测量，这两个值是父View根据子View的width/height及父View和各子View综合测量处理的值
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /** 设置宽度 */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) { // match_parent、accurate
            mWidth = specSize;
        } else {
            int desireByImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth(); // 由图片决定的宽：计算和测量图片本身的大小；
            int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width(); // 由字体决定的宽
            if (specMode == MeasureSpec.AT_MOST) { // wrap_content
                int desire = Math.max(desireByImg, desireByTitle);
                mWidth = Math.max(desire, specSize);
            }
        }

        /** 设置高度 */
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) { //match_parent,accurate
            mHeight = specSize;
        } else {
            int desire = getPaddingTop() + getPaddingBottom() + mImage.getHeight() + mTextBound.height();
            if (specMode == MeasureSpec.AT_MOST) { //wrap_content
                mHeight = Math.min(desire, specSize);
            }
        }

        setMeasuredDimension(mWidth, mHeight); // 设置该View的宽和高；使用measure出来的值；
    }

    /**
     * 绘制View
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        /** 边框 */
        mPaint.setStrokeWidth(4);//设置边框的宽
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);//绘制边框用的颜色
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);//绘制矩形框

        /** 设置图片被绘制的位置区域*/
        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();

        /** 设置画文本的颜色和样式：填充*/
        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        /** 当前设置的宽度小于字体所需的宽度，将字体改为xxx... (截断) */
        if (mTextBound.width() > mWidth) {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mText, paint, mWidth - getPaddingLeft() - getPaddingRight(), TextUtils.TruncateAt.END).toString();//截取字体，将字体变成...的API
            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
        } else {

            /** 正常情况，将字体居中 */
            canvas.drawText(mText, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
        }

        mRect.bottom -= mTextBound.height(); //取消使用掉的块

        if (mImageScale == IMAGE_SCALE_FITXY) {
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        } else {
            //计算居中的矩形范围
            mRect.left = mWidth / 2 - mImage.getWidth() / 2;
            mRect.right = mWidth / 2 + mImage.getWidth() / 2;
            mRect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;
            mRect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;

            canvas.drawBitmap(mImage, null, mRect, mPaint);
        }
    }
}
