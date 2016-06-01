package com.example.zhuzhuxia.qkjr_demo1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by zhuzhuxia on 16/5/9.
 */
public class EvaluationView extends View {

    private int bgColor=Color.GRAY;
    private int objectTextColor=Color.WHITE;
    private int headTextColor=Color.BLACK;
    private int objectTextSize=40;
    private int headTextSize=38;

    private boolean centerInHorizontal,centerInVertical;
    private float startX,startY,stopX,stopY,width,height;

    private Paint headPaint,objectPaint,bgPaint;

    public EvaluationView(Context context) {
        super(context);
        initPaint();

    }


    public EvaluationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EvaluationView, 0, 0);
        try {

            centerInVertical=typedArray.getBoolean(R.styleable.EvaluationView_is_vertical_center,false);
            centerInHorizontal=typedArray.getBoolean(R.styleable.EvaluationView_is_horizontal_center,false);
//            width=typedArray.getDimension(R.styleable.EvaluationView_width,0);
//            height=typedArray.getDimension(R.styleable.EvaluationView_height,0);
            //水平居中
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            int width1 = wm.getDefaultDisplay().getWidth();
            int height1 = wm.getDefaultDisplay().getHeight();
//            if(centerInHorizontal){
//                startX=(width1-width)/2;
//                stopX=startX+width;
//            }
//            if(centerInVertical){
//                startY=(height1-height)/2;
//                stopY=startY+height;
//            }

        } finally {
            typedArray.recycle();
        }
    }

    public EvaluationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //init edge;
        startX=getLeft();
        startY=getTop();
        stopX=getRight();
        stopY=getBottom();

    }


    @Override
    protected void onDraw(Canvas canvas) {

        drawing(canvas);
        super.onDraw(canvas);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    private void initPaint(){

        headPaint=new Paint();
        headPaint.setStyle(Paint.Style.STROKE);
        headPaint.setColor(headTextColor);
        headPaint.setTextSize(headTextSize);
        headPaint.setTextAlign(Paint.Align.CENTER);

        objectPaint=new Paint();
        objectPaint.setStyle(Paint.Style.STROKE);
        objectPaint.setColor(objectTextColor);
        objectPaint.setTextSize(objectTextSize);

        bgPaint=new Paint();
        bgPaint.setColor(bgColor);
        bgPaint.setStyle(Paint.Style.FILL);

    }

    private void drawing(Canvas canvas){

        canvas.drawRoundRect(new RectF(startX,startY,stopX,stopY),10,10,bgPaint);
        float height=stopY-startY;
        float itemHeight=height/5;

        float width=stopX-startX;
        canvas.drawText("大咖水平",width/8,itemHeight*2,objectPaint);
        canvas.drawText("问题解决",width/8,itemHeight*3,objectPaint);
        canvas.drawText("通话质量", width / 8, itemHeight * 4, objectPaint);
        canvas.drawText("匿名反馈",width/2,itemHeight,headPaint);

        

    }



}
