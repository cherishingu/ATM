package Widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;


import com.qkjr_demo2.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhuzhuxia on 16/5/12.
 * 一个倒计时的按钮,点击按钮后,再外部监听器中,调用start()方法.就开始倒计时.
 * 详细的属性见attr文件.主要是使用了Timer与task,每秒刷新一次.
 * 属性maxNum:确定从几开始倒计时.
 */
public class TimeCountView extends Button{


    private String text;
    private int maxNum,color,maxNum2;
    private Paint paint;

    public boolean doing=false;

    public TimeCountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.TimeCountView);
        maxNum2=maxNum=array.getInteger(R.styleable.TimeCountView_maxNum, 60);
        color=array.getInteger(R.styleable.TimeCountView_backgroundcolor, getResources().getColor(R.color.colorAccent));
        text=getText().toString();

    }

    private void initPaints(){
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setText(text);
        if(text.length()<5) {
            setTextSize(TypedValue.COMPLEX_UNIT_DIP,17);
        }

    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 200:
                    invalidate();
                    break;
                case 300:
                    setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape));
                    break;
            }
        }
    };
    Timer timer=null;
    TimerTask task;


    /**
     * 外部调用启动倒计时
     */

    public void start(){
        if(timer==null){

            setClickable(false);
            setBackgroundColor(Color.GRAY);
            doing=true;
            maxNum=maxNum2;
            timer=new Timer();
            task= new TimerTask() {
                @Override
                public void run() {

                    maxNum--;
                    if(maxNum<0){
                        text="重新发送";
                        doing=false;
                        setClickable(true);
                        handler.sendEmptyMessage(300);

                        timer.cancel();
                        timer=null;

                    }
                    else
                        text=maxNum+"秒";

                    handler.sendEmptyMessage(200);
                }
            };
            timer.schedule(task,0,1000);
        }
    }
    public void stop(){
        maxNum=-1;
    }

}
