package com.exampleenen.ruedy.imagelib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exampleenen.ruedy.imagelib.R;


/**
 * Created by 丁瑞 on 2017/4/9.
 */

public class IdentityImageView extends ViewGroup {
    private Context mContext;
    private CircleImageView bigImageView;//大圆
    private CircleImageView smallImageView;//小圆
    private float radiusScale;//小图片与大图片的比例，默认0.28，刚刚好，大了不好看
    int radius;//大图片半径
    private int smallRadius;//小图片半径
    private double angle = 45; //标识角度大小
    private boolean isprogress;//是否可以加载进度条，必须设置为true才能开启
    private int progressCollor;//进度条颜色
    private int borderColor;//边框颜色
    private int borderWidth;//边框、进度条宽度
    private TextView textView;//标识符为文字，用的地方比较少
    private boolean hintSmallView;//标识符是否隐藏
    private Paint mBorderPaint;//边框画笔
    private Paint mProgressPaint;//进度条画笔
    private float progresss;
    private Drawable bigImage;//大图片
    private Drawable smallimage;//小图片
    private int setprogressColor = 0;//动态设置进度条颜色值
    private int totalwidth;
    public IdentityImageView(Context context) {
        this(context, null);
    }

    public IdentityImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IdentityImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setWillNotDraw(false);//是的ondraw方法被执行

        addThreeView();

        initAttrs(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int viewHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int viewWidht = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        switch (viewWidthMode) {
            case MeasureSpec.EXACTLY:   //说明在布局文件中使用的是具体值：100dp或者match_parent
                //为了方便，让半径等于宽高中小的那个，再设置宽高至半径大小
                totalwidth = viewWidht < viewHeight ? viewWidht : viewHeight;
                float scale = 1 + radiusScale;
                int radius2 = totalwidth / 2;
                radius = (int) (radius2 / scale);
                break;
            case MeasureSpec.AT_MOST:  //说明在布局文件中使用的是wrap_content:
                //这时我们也写死宽高
                radius = 200;
                totalwidth = (int) ((radius + radius * radiusScale) * 2);
                break;
            default:
                radius = 200;
                totalwidth = (int) ((radius + radius * radiusScale) * 2);
                break;
        }
        setMeasuredDimension(totalwidth, totalwidth);
        adjustThreeView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initPaint();
        if (isprogress) {
            drawProgress(canvas);
        }
        if (borderWidth > 0) {
            drawBorder(canvas);
        }

    }

    /**
     * 画边框
     *
     * @param canvas 画布
     */
    private void drawBorder(Canvas canvas) {
        canvas.drawCircle(totalwidth/2, totalwidth/2, radius - borderWidth / 2, mBorderPaint);
    }

    //画圆弧进度条
    private void drawProgress(Canvas canvas) {
        RectF rectf = new RectF(smallRadius+borderWidth / 2, smallRadius+borderWidth / 2, getWidth() -smallRadius- borderWidth / 2, getHeight()-smallRadius - borderWidth / 2);
        //定义的圆弧的形状和大小的范围,之所以减去圆弧的一半，是因为画圆环的高度时，
        // 原因就在于setStrokeWidth这个方法，并不是往圆内侧增加圆环宽度的，而是往外侧增加一半，往内侧增加一半。
        canvas.drawArc(rectf, (float) angle, progresss, false, mProgressPaint);

    }

    private void initPaint() {
        if (mBorderPaint == null) {
            mBorderPaint = new Paint();
            mBorderPaint.setStyle(Paint.Style.STROKE);
            mBorderPaint.setAntiAlias(true);
        }
        mBorderPaint.setColor(borderColor);
        mBorderPaint.setStrokeWidth(borderWidth);

        if (mProgressPaint == null) {
            mProgressPaint = new Paint();
            mProgressPaint.setStyle(Paint.Style.STROKE);
            mProgressPaint.setAntiAlias(true);
        }
        if (setprogressColor != 0) {
            mProgressPaint.setColor(getResources().getColor(setprogressColor));
        } else mProgressPaint.setColor(progressCollor);

        mProgressPaint.setStrokeWidth(borderWidth);

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //重点在于smallImageView的位置确定,默认为放在右下角，可自行拓展至其他位置


        double cos = Math.cos(angle * Math.PI / 180);
        double sin = Math.sin(angle * Math.PI / 180);
        double left = totalwidth/2 + (radius * cos - smallRadius);
        double top = totalwidth/2 + (radius * sin - smallRadius);
        int right = (int) (left + 2 * smallRadius);
        int bottom = (int) (top + 2 * smallRadius);
        bigImageView.layout(smallRadius+borderWidth / 2, smallRadius+borderWidth / 2, totalwidth-smallRadius - borderWidth / 2, totalwidth-smallRadius - borderWidth / 2);
        textView.layout((int) left, (int) top, right, bottom);
        smallImageView.layout((int) left, (int) top, right, bottom);

    }

    private void adjustThreeView() {
        bigImageView.setLayoutParams(new LayoutParams(radius, radius));
        smallRadius = (int) (radius * radiusScale);
        smallImageView.setLayoutParams(new LayoutParams(smallRadius, smallRadius));
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

    private void addThreeView() {


        bigImageView = new CircleImageView(mContext);//大圆

        smallImageView = new CircleImageView(mContext);//小圆

        textView = new TextView(mContext);//文本
        textView.setGravity(Gravity.CENTER);

        addView(bigImageView, 0, new LayoutParams(radius, radius));


        smallRadius = (int) (radius * radiusScale);

        addView(smallImageView, 1, new LayoutParams(smallRadius, smallRadius));


        addView(textView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        smallImageView.bringToFront();//使小图片位于最上层


    }


    private void initAttrs(AttributeSet attrs) {
        TintTypedArray tta = TintTypedArray.obtainStyledAttributes(getContext(), attrs,

                R.styleable.IdentityImageView);
        bigImage = tta.getDrawable(R.styleable.IdentityImageView_iciv_bigimage);
        smallimage = tta.getDrawable(R.styleable.IdentityImageView_iciv_smallimage);
        angle = tta.getFloat(R.styleable.IdentityImageView_iciv_angle, 45);//小图以及进度条起始角度
        radiusScale = tta.getFloat(R.styleable.IdentityImageView_iciv_radiusscale, 0.28f);//大图和小图的比例
        isprogress = tta.getBoolean(R.styleable.IdentityImageView_iciv_isprogress, false);
        //是否要进度条，不为true的话，设置，进度条颜色和宽度也没用

        progressCollor = tta.getColor(R.styleable.IdentityImageView_iciv_progress_collor, 0);//边框进度条颜色
        borderColor = tta.getColor(R.styleable.IdentityImageView_iciv_border_color, 0);//边框颜色
        borderWidth = tta.getInteger(R.styleable.IdentityImageView_iciv_border_width, 0);//边框宽（，同为进度条）
        hintSmallView = tta.getBoolean(R.styleable.IdentityImageView_iciv_hint_smallimageview, false);//隐藏小图片
        if (hintSmallView) {
            smallImageView.setVisibility(GONE);
        }
        if (bigImage != null) {
            bigImageView.setImageDrawable(bigImage);
        }
        if (smallimage != null) {
            smallImageView.setImageDrawable(smallimage);
        }

    }

    /**
     * 获得textview
     *
     * @return textView
     */
    public TextView getTextView() {
        if (textView != null) return textView;
        else return null;
    }

    /**
     * 获得大图片
     *
     * @return bigImageView
     */
    public CircleImageView getBigCircleImageView() {
        if (bigImageView != null) return bigImageView;
        else return null;
    }

    /**
     * 获得小图片
     *
     * @return smallImageView
     */
    public CircleImageView getSmallCircleImageView() {
        if (smallImageView != null)
            return smallImageView;
        else return null;
    }

    /**
     * 设置进度条进度，一共360
     *
     * @param angle 进度大小
     */
    public void setProgress(float angle) {
        if (progresss == angle) {
            return;
        }
        progresss = angle;
        requestLayout();
        invalidate();
    }

    /**
     * 设置标识的角度
     *
     * @param angles 角度
     */
    public void setAngle(int angles) {
        if (angles == angle) return;
        angle = angles;
        requestLayout();
        invalidate();
    }

    /**
     * 设置标识的大小
     *
     * @param v 比例
     */
    public void setRadiusScale(float v) {
        if (v == radiusScale) return;
        radiusScale = v;
        requestLayout();
        invalidate();

    }

    /**
     * 设置是否可以有进度条
     *
     * @param b 是否有进度条
     */
    public void setIsprogress(boolean b) {

        if (b == isprogress) return;
        isprogress = b;
        requestLayout();
        invalidate();
    }

    /**
     * 设置填充的颜色
     *
     * @param color 边框颜色
     */
    public void setBorderColor(int color) {
        if (color == borderColor) return;
        borderColor = color;
        requestLayout();
        invalidate();

    }

    /**
     * 设置进度条颜色
     *
     * @param color 进度条颜色
     */
    public void setProgressColor(int color) {
        if (color == progressCollor) return;
        setprogressColor = color;
        requestLayout();
        invalidate();
    }

    /**
     * @param width 边框和进度条宽度
     */
    public void setBorderWidth(int width) {
        if (width == borderWidth) return;
        ;
        borderWidth = width;
        requestLayout();//重走onLayout方法
        invalidate();//重走onDraw方法
    }
}
