package com.gloomyer.securitycode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * 验证码
 *
 * @author Gloomy
 * @date 2016年12月09日14:20:39
 * @blog https://www.gloomyer.com
 */
public class SecurityCode extends View implements View.OnClickListener {

    private float mTextSize;
    private Paint mTextPaint;
    private Paint mBgPaint;
    private int mTextLength;
    private int mTextColor;
    private int mBgColor;
    private String mText;
    private Rect mTextBound;
    private static final String PARAMS
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public SecurityCode(Context context) {
        this(context, null);
    }

    public SecurityCode(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecurityCode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SecurityCode);
        mTextSize = typedArray.getDimension(R.styleable.SecurityCode_TextSize, -1);
        if (mTextSize == -1) {
            mTextSize = dp2px(context, 14);
        }
        mTextLength = typedArray.getInteger(R.styleable.SecurityCode_TextLength, 4);
        mTextColor = typedArray.getColor(R.styleable.SecurityCode_TextColor, Color.BLACK);
        mBgColor = typedArray.getColor(R.styleable.SecurityCode_bg, Color.WHITE);

        typedArray.recycle();


        mBgPaint = new Paint();
        mBgPaint.setColor(mBgColor);
        setOnClickListener(this);


        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        randomText();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), mBgPaint);
        canvas.drawText(mText,
                canvas.getWidth() / 2 - mTextBound.width() / 2,
                canvas.getHeight() / 2 + mTextBound.height() / 2,
                mTextPaint);
    }

    public int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        randomText();
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = mTextBound.width() + getPaddingLeft() + getPaddingRight();
        }

        int height;
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = mTextBound.height() + getPaddingBottom() + getPaddingTop();
        }

        setMeasuredDimension(width, height);
    }

    /**
     * 生成验证码,如果需要复杂的，需要改写该方法
     */
    private void randomText() {
        //生成随机字符
        StringBuffer sb = new StringBuffer(mTextLength);
        for (int i = 0; i < mTextLength; i++) {
            int index = new Random().nextInt(PARAMS.length());
            sb.append(PARAMS.charAt(index));
        }
        mText = sb.toString();

        if (mTextBound == null)
            mTextBound = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
    }

    public String getText() {
        return mText;
    }
}
