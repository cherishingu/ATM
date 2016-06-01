package Widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import business.CompleteInfoActivity2;


/**
 * Created by jin on 2016/5/17.
 */
public class CustomDialog2 extends  Dialog {

    int layoutRes;//布局文件
    Context context;
    String data = null;

    public CustomDialog2(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 自定义布局的构造方法
     *
     * @param context
     * @param resLayout
     */
    public CustomDialog2(Context context, int resLayout) {
        super(context);
        this.context = context;
        this.layoutRes = resLayout;
        Log.d("CustemDialog1", "构造函数初始化成功");
    }

    /**
     * 自定义主题及布局的构造方法
     *
     * @param context
     * @param theme
     * @param resLayout
     */
    public CustomDialog2(Context context, int theme, int resLayout) {
        super(context, theme);
        this.context = context;
        this.layoutRes = resLayout;
        Log.d("CustemDialog1", "构造函数初始化成功");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layoutRes);
//            View view = findViewById(R.id.dialog_parent);
//        view.getBackground().setAlpha(200);
        final TextView tev1 = (TextView) findViewById(R.id.tev1);
        final TextView tev2 = (TextView) findViewById(R.id.tev2);
        final TextView tev3 = (TextView) findViewById(R.id.tev3);
        final TextView tev4 = (TextView) findViewById(R.id.tev4);
        final TextView tev5 = (TextView) findViewById(R.id.tev5);
        final TextView tev6 = (TextView) findViewById(R.id.tev6);

        Log.d("CustemDialog1", "onCreate 初始化成功");
        tev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CustemDialog1", "tev1获得焦点事件");
                tev1.setBackgroundResource(R.drawable.linelayborderfocus);
//                tev2.setBackgroundResource(R.drawable.linelaybordernomal);
//                tev3.setBackgroundResource(R.drawable.linelaybordernomal);
                CompleteInfoActivity2.relaship1Edit.setText(tev1.getText().toString());
                CustomDialog2.this.dismiss();
                data = tev1.getText().toString();
            }
        });
        tev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tev2.setBackgroundResource(R.drawable.linelayborderfocus);
//                tev1.setBackgroundResource(R.drawable.linelaybordernomal);
//                tev3.setBackgroundResource(R.drawable.linelaybordernomal);
                CompleteInfoActivity2.relaship1Edit.setText(tev2.getText().toString());
                CustomDialog2.this.dismiss();
                data = tev2.getText().toString();
            }
        });
        tev3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        tev3.setBackgroundResource(R.drawable.linelayborderfocus);
                                        CompleteInfoActivity2.relaship1Edit.setText(tev3.getText().toString());
                                        CustomDialog2.this.dismiss();
                                    }
                                });
//
        tev4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tev4.setBackgroundResource(R.drawable.linelayborderfocus);
                CompleteInfoActivity2.relaship1Edit.setText(tev4.getText().toString());
                CustomDialog2.this.dismiss();
            }
        });
        tev5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tev5.setBackgroundResource(R.drawable.linelayborderfocus);
                CompleteInfoActivity2.relaship1Edit.setText(tev5.getText().toString());
                CustomDialog2.this.dismiss();
            }
        });
        tev6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tev6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tev6.setBackgroundResource(R.drawable.linelayborderfocus);
                        CompleteInfoActivity2.relaship1Edit.setText("");
                        CustomDialog2.this.dismiss();
                    }
                });
            }
        });
//        Button btn1 = (Button) findViewById(R.id.btn1);
//        final EditText btn2 = (EditText) findViewById(R.id.btn2);
//        Button btn3 = (Button) findViewById(R.id.btn3);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                CustomDialog2.this.dismiss();
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (btn2.getText().toString() != null && !btn2.getText().toString().equals("")) {
//                    CompleteInfoActivity2.relaship1Edit.setText(btn2.getText().toString());
//                    CustomDialog2.this.dismiss();
//                } else if (data != null) {
//                    CompleteInfoActivity2.relaship1Edit.setText(data);
//                    CustomDialog2.this.dismiss();
//                }
//            }
//        });
    }
}
