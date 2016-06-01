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
public class CustemDialog1 extends  Dialog {

        int layoutRes;//布局文件
        Context context;
        String data=null;

        public CustemDialog1(Context context) {
            super(context);
            this.context = context;
        }

        /**
         * 自定义布局的构造方法
         *
         * @param context
         * @param resLayout
         */
        public CustemDialog1(Context context, int resLayout) {
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
        public CustemDialog1(Context context, int theme, int resLayout) {
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
            final TextView tev1=(TextView)findViewById(R.id.tev1);
            final  TextView tev2=(TextView)findViewById(R.id.tev2);
            final  TextView tev3=(TextView)findViewById(R.id.tev3);
            final   TextView tev4=(TextView)findViewById(R.id.tev4);
            final   TextView tev5=(TextView)findViewById(R.id.tev5);
            final    TextView tev6=(TextView)findViewById(R.id.tev6);
//            Button btn1=(Button)findViewById(R.id.btn1);
//            final EditText btn2=(EditText)findViewById(R.id.btn2);
//            Button btn3=(Button)findViewById(R.id.btn3);

            Log.d("CustemDialog1", "onCreate 初始化成功");
            tev1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CustemDialog1", "tev1获得焦点事件");
                    tev1.setBackgroundResource(R.drawable.linelayborderfocus);
                    CompleteInfoActivity2.relashipEdit.setText(tev1.getText().toString());
                    CustemDialog1.this.dismiss();
//                    tev2.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev3.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev4.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev5.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev6.setBackgroundResource(R.drawable.linelaybordernomal);
                    data=tev1.getText().toString();
                }
            });
            tev2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tev2.setBackgroundResource(R.drawable.linelayborderfocus);
                    CompleteInfoActivity2.relashipEdit.setText(tev2.getText().toString());
                    CustemDialog1.this.dismiss();
//                    tev1.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev3.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev4.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev5.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev6.setBackgroundResource(R.drawable.linelaybordernomal);
                    data=tev2.getText().toString();
                }
            });
            tev3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tev3.setBackgroundResource(R.drawable.linelayborderfocus);
                    CompleteInfoActivity2.relashipEdit.setText(tev3.getText().toString());
                    CustemDialog1.this.dismiss();
//                    tev2.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev1.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev4.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev5.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev6.setBackgroundResource(R.drawable.linelaybordernomal);
                    data=tev3.getText().toString();
                }
            });
            tev4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tev4.setBackgroundResource(R.drawable.linelayborderfocus);
                    CompleteInfoActivity2.relashipEdit.setText(tev4.getText().toString());

                    CustemDialog1.this.dismiss();
//                    tev2.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev3.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev1.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev5.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev6.setBackgroundResource(R.drawable.linelaybordernomal);
                    data=tev4.getText().toString();
                }
            });
            tev5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tev5.setBackgroundResource(R.drawable.linelayborderfocus);
                    CompleteInfoActivity2.relashipEdit.setText(tev5.getText().toString());
                    CustemDialog1.this.dismiss();
//                    tev2.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev3.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev4.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev1.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev6.setBackgroundResource(R.drawable.linelaybordernomal);
                    data=tev5.getText().toString();
                }
            });
            tev6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tev6.setBackgroundResource(R.drawable.linelayborderfocus);
                    CompleteInfoActivity2.relashipEdit.setText("");
                    CustemDialog1.this.dismiss();
//                    tev2.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev3.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev4.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev5.setBackgroundResource(R.drawable.linelaybordernomal);
//                    tev1.setBackgroundResource(R.drawable.linelaybordernomal);
                    data=tev6.getText().toString();
                }
            });

//
//            btn1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    CustemDialog1.this.dismiss();
//                }
//            });
////            btn2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
////                @Override
////                public void onFocusChange(View v, boolean hasFocus) {
////                    if (hasFocus) {
////                        CustemDialog1.this.dismiss();
////                        Log.d("CustemDialog1","获得焦点");
////                    }
////                }
////            });
//            btn3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(btn2.getText().toString()!=null&&!btn2.getText().toString().equals(""))
//                    {
//                        CompleteInfoActivity2.relashipEdit.setText(btn2.getText().toString());
//                        CustemDialog1.this.dismiss();
//                    }
//                    else if (data!=null)
//                    {
//                        CompleteInfoActivity2.relashipEdit.setText(data);
//                        CustemDialog1.this.dismiss();
//                    }
//                }
//            });
        }
    }

