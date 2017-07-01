package com.example.administrator.timeline.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.example.administrator.timeline.R;
import com.example.administrator.timeline.utils.UIUtils;
import java.util.List;

/**
 * Created by Juwuguo on 2017/6/27.
 */

public class MedicTimeLineView extends View {

    private Context mContext;

    /**
     * View的宽度，高度
     */
    private int mWidth, mHeight;
    /**
     * 柱状图条的高度
     */
    private int mChartHeight;
    /**
     * 虚线宽度
     */
    private int mLineWidth;
    /**
     * 柱状图正常颜色
     */
    private int mChartNormalColor;
    /**
     * 柱状图部分异常颜色
     */
    private int mChartPartAbnormalColor;
    /**
     * 柱状图全部异常颜色
     */
    private int mChartAbnormalColor;
    /**
     * 文字颜色
     */
    private int mTextColor;
    /**
     * 柱状图背景圆的颜色
     */
    private int mCircleColor;
    /**
     * 柱状图背景圆的颜色
     */
    private int mCircleSize;
    /**
     * 文字大小
     */
    private int mTextSize;

    private List<String> mStatus;
    /**
     * 药名
     */
    private String mYHighValue;
    /**
     * 有数据的天数
     */
    private List<Integer> listTag;

    /**
     * 有数据的天数处于开头还是结尾的状态
     */
    private List<Integer> xDayValues;

    /**
     * 有数据的天数处于开头还是结尾的状态
     */
    private List<Integer> xDaySize;
    /**
     * 柱状图画笔
     */
    private Paint mChartPaint;

    /**
     * 柱状图背景圆画笔
     */
    private Paint mChartBgCirclePaint;
    /**
     * 文字画笔
     */
    private Paint mTextPaint;
    /**
     * 虚线画笔
     */
    private Paint mLinePaint;
    /**
     * 文字范围
     */
    private Rect mTextBound;
    /**
     * X轴的间距
     */
    private int mXScale;

    /**
     * 两周或一个月
     */
    private int daySize;

    /**
     * 为坐标文本留出的间隔
     */
    private int mInterval;

    public MedicTimeLineView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MedicTimeLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttrs(attrs);
        initData();
    }

    public MedicTimeLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initAttrs(attrs);
        initData();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MedicTimeLineView);
        //柱状图条的高度
        mChartHeight = (int) typedArray.getDimension(R.styleable.MedicTimeLineView_timeLine_chartHeight, UIUtils.dp2px(10));
        //虚线宽度
        mLineWidth = (int) typedArray.getDimension(R.styleable.MedicTimeLineView_timeLine_lineWidth, UIUtils.dp2px(1));
        //柱状图正常颜色
        mChartNormalColor = typedArray
                .getColor(R.styleable.MedicTimeLineView_timeLine_chartNormalColor, getResources().getColor(R.color.c_2bb2ba));
        //柱状图部分异常颜色
        mChartPartAbnormalColor = typedArray
                .getColor(R.styleable.MedicTimeLineView_timeLine_chartPartAbnormalColor, getResources().getColor(R.color.c_ff862e));
        //柱状图全部异常颜色
        mChartAbnormalColor = typedArray
                .getColor(R.styleable.MedicTimeLineView_timeLine_chartAbnormalColor, getResources().getColor(R.color.c_e14434));
        //文字颜色
        mTextColor = typedArray
                .getColor(R.styleable.MedicTimeLineView_timeLine_textColor, getResources().getColor(R.color.c_333333));
        //文字大小
        mTextSize = (int) typedArray.getDimension(R.styleable.MedicTimeLineView_timeLine_textSize, UIUtils.sp2px(14));
        //背景圆的颜色
        mCircleColor = typedArray.getColor(R.styleable.MedicTimeLineView_timeLine_circleolor, getResources().getColor(R.color.c_e0e0e0));

        //背景圆的大小
        mCircleSize = (int) typedArray.getDimension(R.styleable.MedicTimeLineView_timeLine_circlesize, UIUtils.sp2px(2));
        typedArray.recycle();
    }


    private void initData() {
        mInterval = UIUtils.dp2px(3);

        //初始化画笔
        mChartPaint = new Paint();
        mChartPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextBound = new Rect();

        mChartBgCirclePaint = new Paint();
        mChartBgCirclePaint.setAntiAlias(true);
        mChartBgCirclePaint.setColor(mCircleColor);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(mCircleColor);
        mLinePaint.setStrokeWidth(mLineWidth);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setPathEffect(new DashPathEffect(new float[]{30, 20}, 0));   //虚线
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY) {
            mWidth = widthSpecSize;
        } else {
            mWidth = UIUtils.dp2px(400);
        }
        if (heightSpecMode == MeasureSpec.EXACTLY) {
            mHeight = UIUtils.dp2px(60);
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    public void setData(String yHighValue, List<Integer> xDayValues, List<Integer> xDaySize, List<String> status, List<Integer> listTag,
            int daySize) {
        this.mYHighValue = yHighValue;
        this.xDayValues = xDayValues;
        this.xDaySize = xDaySize;
        this.mStatus = status;
        this.listTag = listTag;
        this.daySize = daySize;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制Y轴的文字(药名)
        drawAxisYValues(canvas);

        //绘制背景小圆
        drawAxisXBgValues(canvas);

        drawEveryDataBar(canvas);
    }

    /**
     * 绘制Y轴的文字(药名)
     */
    private void drawAxisYValues(Canvas canvas) {
        mTextPaint.getTextBounds(mYHighValue, 0, mYHighValue.length(), mTextBound);
        canvas.drawText(mYHighValue, getPaddingLeft(), mHeight / 2 - mInterval - 0.5f * (mChartHeight + mTextBound.height()), mTextPaint);
    }

    /**
     * 绘制柱形图背景圆
     */
    private void drawAxisXBgValues(Canvas canvas) {
        int circleNumber = (mWidth - getPaddingLeft() - getPaddingRight()) / (mCircleSize + mInterval);
        for (int i = 0; i < circleNumber; i++) {
            canvas.drawCircle(i * (mInterval + mCircleSize) + mCircleSize / 2, mHeight / 2, mCircleSize, mChartBgCirclePaint);
        }
    }

    /**
     * 画矩形
     canvas.drawRect(rectF,mChartPaint);
     */

    /**
     * 画左右半圆
     canvas.drawRoundRect(rectF, mChartHeight / 2, mChartHeight / 2, mChartPaint);
     */
    /**
     * 画左边半圆
     *canvas.drawRoundRect(rectF, mChartHeight / 2, mChartHeight / 2, mChartPaint);
     *canvas.drawRect(new RectF(rectF.left + mChartHeight / 2, rectF.top, rectF.right, rectF.bottom), mChartPaint);
     * */
    /**
     * 画右边半圆
     * canvas.drawRoundRect(rectF, mChartHeight / 2, mChartHeight / 2, mChartPaint);
     * canvas.drawRect(new RectF(rectF.left, rectF.top, rectF.right- mChartHeight / 2, rectF.bottom), mChartPaint);
     */

    private void drawEveryDataBar(Canvas canvas) {
        mXScale = (mWidth - getPaddingLeft() - getPaddingRight()) / daySize;
        for (int i = 0; i < xDayValues.size(); i++) {
            if (mStatus.get(i).equals("NORMAL")) {
                mChartPaint.setColor(mChartNormalColor);
            } else if (mStatus.get(i).equals("ABNORMAL")) {
                mChartPaint.setColor(mChartAbnormalColor);
            } else if (mStatus.get(i).equals("PARTIAL_EXCEPTION")) {
                mChartPaint.setColor(mChartPartAbnormalColor);
            }
            RectF rectF = new RectF();
            rectF.left = getPaddingLeft() + xDayValues.get(i) * mXScale;
            rectF.top = mHeight / 2 - mInterval - mCircleSize;
            rectF.right = getPaddingLeft() + (xDayValues.get(i) + xDaySize.get(i)) * mXScale;
            rectF.bottom = mHeight / 2 + mChartHeight / 2;
            canvas.drawRoundRect(rectF, mChartHeight / 2, mChartHeight / 2, mChartPaint);
            if (listTag.get(i) == -1) {
                canvas.drawRoundRect(rectF, mChartHeight / 2, mChartHeight / 2, mChartPaint);
                canvas.drawRect(new RectF(rectF.left + mChartHeight / 2, rectF.top, rectF.right, rectF.bottom), mChartPaint);
            } else if (listTag.get(i) == 0) {
                canvas.drawRect(rectF, mChartPaint);
            } else if (listTag.get(i) == 1) {
                canvas.drawRoundRect(rectF, mChartHeight / 2, mChartHeight / 2, mChartPaint);
                canvas.drawRect(new RectF(rectF.left, rectF.top, rectF.right - mChartHeight / 2, rectF.bottom), mChartPaint);
            } else if (listTag.get(i) == 2) {
                canvas.drawRoundRect(rectF, mChartHeight / 2, mChartHeight / 2, mChartPaint);
            }

        }


    }

}
