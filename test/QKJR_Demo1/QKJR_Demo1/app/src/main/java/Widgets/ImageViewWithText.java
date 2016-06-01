package Widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import Utils.LogUtils;

/**
 * Created by zhuzhuxia on 16/5/13.
 */
public class ImageViewWithText extends LinearLayout {

    private ImageView imageView;
    private TextView textView;
    private String textValue;
    private float textSize;
    private int textColor;
    private int imgSrc;

    public ImageViewWithText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.ImageViewWithText);

        textColor=array.getInt(R.styleable.ImageViewWithText_textcolor, Color.BLACK);
        textValue=array.getString(R.styleable.ImageViewWithText_textvalue);
        textSize=array.getDimension(R.styleable.ImageViewWithText_text_size,13);
        initViews(context);
        LogUtils.logD("view初始化");
    }


    public ImageViewWithText(Context context){
        super(context);

        initViews(context);
        LogUtils.logD("view初始化");
    }

    private void initViews(Context context){
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.view_btnwithtext, this);
        imageView= (ImageView) view.findViewById(R.id.image_view1);
        textView= (TextView) view.findViewById(R.id.text_view1);
        textView.setText(textValue);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(textSize);
        LogUtils.logD("view设置汉字");
    }

    public void setImg(int imgId){
        imageView.setImageResource(imgId);
        LogUtils.logD("view设置图片");
    }

}
