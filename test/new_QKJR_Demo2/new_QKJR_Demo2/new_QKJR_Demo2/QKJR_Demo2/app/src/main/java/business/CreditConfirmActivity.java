package business;
/**
 * 个人信息下按下保存键后跳到的信用认证界面
 * Created by Administrator on 2016/5/25.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.qkjr_demo2.R;

import Widgets.ActivityCollector;

public class CreditConfirmActivity extends AppCompatActivity {

    Button nextBtn;
    CheckBox checkBox;
    boolean isCheck = false;
    ImageView creditBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_credit_confirm);
        creditBack = (ImageView) findViewById(R.id.credit_back);
        nextBtn = (Button) findViewById(R.id.next_btn);
        checkBox = (CheckBox) findViewById(R.id.credit_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheck = isChecked;
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck == true) {
                    startActivity(new Intent(CreditConfirmActivity.this, CreditConfirmActivity2.class));
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 0.5f;
                    lp.dimAmount = 0.5f;
                    getWindow().setAttributes(lp);
                }
                else{
                    Toast.makeText(CreditConfirmActivity.this, "请勾选，否则不能提交申请。", Toast.LENGTH_SHORT).show();
                }
            }
        });
        creditBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(CreditConfirmActivity.this, PersonalDetailActivity.class));
            }
        });
    }

}
