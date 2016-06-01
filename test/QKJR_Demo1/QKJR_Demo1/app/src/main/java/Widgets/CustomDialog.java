package Widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhuzhuxia.qkjr_demo1.R;

import Utils.ActivityManagerApplication;
import business.MainActivity;


/**
 * Created by jin on 2016/5/17.
 */
public class CustomDialog extends Dialog {
    int layoutRes;//布局文件
    Context context;
    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }
    /**
     * 自定义布局的构造方法
     * @param context
     * @param resLayout
     */
    public CustomDialog(Context context,int resLayout){
        super(context);
        this.context = context;
        this.layoutRes=resLayout;
    }
    /**
     * 自定义主题及布局的构造方法
     * @param context
     * @param theme
     * @param resLayout
     */
    private Activity activity;
    public CustomDialog(Activity activity,Context context, int theme,int resLayout){
        super(context, theme);
        this.context = context;
        this.layoutRes=resLayout;
        this.activity=activity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layoutRes);
        View view=findViewById(R.id.dialog_parent);
//        view.getBackground().setAlpha(200);
        Button btn=(Button)findViewById(R.id.dialog_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.this.dismiss();
                Intent intent=new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
                ActivityManagerApplication.destoryActivity("complete1");
                ActivityManagerApplication.destoryActivity("complete2");
                ActivityManagerApplication.destoryActivity("complete3");

            }
        });
    }
}
