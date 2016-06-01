package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qkjr_demo2.R;

import Widgets.ActivityCollector;

/**
 * 输入密码还款
 * Created by Administrator on 2016/5/25.
 */

public class InputPwdActivity extends BaseActivity implements View.OnClickListener {
    private ImageView input_pwd_back;
    private Button input_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_input_pwd);
        initView();
        initEvents();
    }
    public void initView(){
        input_pwd_back = (ImageView)findViewById(R.id.input_pwd_back);
        input_pwd = (Button)findViewById(R.id.input_pwd);
    }
    public void initEvents(){
        input_pwd_back.setOnClickListener(this);
        input_pwd.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.input_pwd_back:
                startActivity(new Intent(InputPwdActivity.this, RepaymentAllActivity.class));
                break;
            case R.id.input_pwd:
                startActivity(new Intent(InputPwdActivity.this, RepaySuccessActivity.class));
        }
    }
}
