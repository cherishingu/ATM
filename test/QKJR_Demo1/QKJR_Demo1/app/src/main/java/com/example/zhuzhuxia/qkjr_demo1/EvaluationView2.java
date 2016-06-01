package com.example.zhuzhuxia.qkjr_demo1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by zhuzhuxia on 16/5/9.
 */
public class EvaluationView2 extends LinearLayout {


    private NumberPicker numberPicker1,numberPicker2,numberPicker3;
    private View view;
    private TextView textView;

    public EvaluationView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.evaluation_view, this);

        numberPicker1= (NumberPicker) view.findViewById(R.id.number1);
        numberPicker2= (NumberPicker) view.findViewById(R.id.number2);
        numberPicker2= (NumberPicker) view.findViewById(R.id.number2);
        textView= (TextView) findViewById(R.id.text_view);


        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    private void init() {

        numberPicker1.setValue(0);
        numberPicker1.setMaxValue(10);
        numberPicker1.setMinValue(0);


    }
}
