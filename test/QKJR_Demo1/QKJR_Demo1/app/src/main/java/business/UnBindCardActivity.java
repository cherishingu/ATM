package business;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import Utils.UIUtils;

public class UnBindCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unbindcard);
        initView();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    private Button unbindcardBtn;
    private ImageView redioBtn1;
    private ImageView redioBtn2;
    private boolean btn1=true;
    private boolean btn2=false;
    private void initView()
    {
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
        unbindcardBtn=(Button)findViewById(R.id.unbindcard_btn);
        redioBtn1=(ImageView)findViewById(R.id.redio_btn1);
        redioBtn2=(ImageView)findViewById(R.id.redio_btn2);
        redioBtn1.setOnClickListener(listener);
        redioBtn2.setOnClickListener(listener);
        unbindcardBtn.setOnClickListener(listener);
    }
    private View.OnClickListener listener =new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            dealRadioBtn(v);
        }
    };
    private  void dealRadioBtn(View v) {
        switch (v.getId()) {
            case R.id.redio_btn1:
                if(btn1==true){
                    redioBtn1.setBackgroundResource(R.mipmap.unchecked_rediobtn);
                }
                else {
                    redioBtn1.setBackgroundResource(R.mipmap.checked_rediobtn);
                }
                btn1=!btn1;
                break;
            case R.id.redio_btn2:
                if (btn2==false)
                {
                    redioBtn2.setBackgroundResource(R.mipmap.checked_rediobtn);
                }
                else {
                    redioBtn2.setBackgroundResource(R.mipmap.unchecked_rediobtn);
                }
                btn2=!btn2;
                break;
            case R.id.unbindcard_btn:
                if(btn1==false&&btn2==false)
                {
                    break;
                }
                else {
                    loadDialog();
                    break;
                }
        }
    }
    private void loadDialog() {
        final Dialog dialog=new UncardBindDialog(UnBindCardActivity.this,R.style.dialog,R.layout.dialog);
        dialog.show();//必须先获得焦点才能加载自定义的dialog组件
        TextView dialogTev= (TextView)dialog.getWindow().getDecorView().findViewById(R.id.d_text);//调用这两个方法才能获dialog组件
        Button dialogBtn= (Button)dialog.getWindow().getDecorView().findViewById(R.id.dialog_btn);
        dialogTev.setText("解绑成功");
        dialogBtn.setText("完   成");
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
                onBackPressed();
            }
        });
    }
}
