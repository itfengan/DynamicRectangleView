package com.fengan.dynamicrectangledemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by fengan on 2017/10/11/011.
 */

public class DynamicRectangleView extends View {

    // Default  values
    private final static int DEFAULT_START_COLOR = Color.parseColor("#88D94600");
    private final static int DEFAULT_END_COLOR = Color.parseColor("#D94600");
    private final static float DEFAULT_PERCENT = (float) 0.5;//右边高度占左边高度的百分比
    private final static float DEFAULT_LIMIT_PERCENT = (float) 0.2;//最小高度占左边高度的百分比

    private Paint mPaint;
    private float OriginalRightHeight;
    private float OriginalLeftHeight;
    private float currentRightHeight;
    private float currentLeftHeight;
    private float minHeight;
    private float maxHeight;
    private int mStartColor;
    private int mEndColor;
    private float mPercent;
    private float mLimitPercent;
    private Path mPath;


    public DynamicRectangleView(Context context) {
        this(context, null);
    }

    public DynamicRectangleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public DynamicRectangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // Retrieve attributes from xml
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DynamicRectangleView);
        try {
            mPercent = typedArray.getFloat(R.styleable.DynamicRectangleView_fengan_percent, DEFAULT_PERCENT);
            mLimitPercent = typedArray.getFloat(R.styleable.DynamicRectangleView_fengan_limit_percent, DEFAULT_LIMIT_PERCENT);
            mStartColor = typedArray.getColor(R.styleable.DynamicRectangleView_fengan_start_color, DEFAULT_START_COLOR);
            mEndColor = typedArray.getColor(R.styleable.DynamicRectangleView_fengan_end_color, DEFAULT_END_COLOR);
        } finally {
            typedArray.recycle();
        }
        initView(context);
    }

    private void initView(Context context) {
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setAntiAlias(true);
//        mPaint.setColor(Color.RED);//纯色
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        OriginalLeftHeight = getMeasuredHeight();
        OriginalRightHeight = getMeasuredHeight()*mPercent;
        minHeight = getMeasuredHeight() *mLimitPercent;
        maxHeight = OriginalRightHeight;
        //设置当前高度
        currentRightHeight = OriginalRightHeight;
        currentLeftHeight = OriginalLeftHeight;
        // LinearGradient 第一个参数第二个参数为 起始位置x,y  三四参数为终点位置x,y。
        // 如果x不变则为y轴渐变， y不变则为x轴渐变
        // 第五个参数为颜色渐变，此处为红色渐变为绿色
        // 第七个参数为渐变次数，可repeat
        Shader mShader = new LinearGradient(0, 0, maxHeight, maxHeight,
                new int[]{mStartColor, mEndColor},
                null, Shader.TileMode.CLAMP);
        // Shader.TileMode三种模式
        // REPEAT:沿着渐变方向循环重复
        // CLAMP:如果在预先定义的范围外画的话，就重复边界的颜色
        // MIRROR:与REPEAT一样都是循环重复，但这个会对称重复
        mPaint.setShader(mShader);// 用Shader中定义定义的颜色来话
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.lineTo(getMeasuredWidth(), 0);
        mPath.lineTo(getMeasuredWidth(), currentRightHeight);
        mPath.lineTo(0, currentLeftHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    public void setPercent(float percent) {
        Log.e("fengan", "percent=" + percent);
        currentRightHeight = OriginalRightHeight * (1 - percent);
        currentLeftHeight = OriginalLeftHeight * (1 - percent);
        if (currentLeftHeight < minHeight) {
            currentLeftHeight = minHeight;
        }
        if (currentRightHeight < minHeight) {
            currentRightHeight = minHeight;
        }
        postInvalidate();
    }
}
