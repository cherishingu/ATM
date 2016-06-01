package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.qkjr_demo2.MainActivity;
import com.qkjr_demo2.MainActivity3;
import com.qkjr_demo2.R;

import Utils.ActivityManagerApplication;
import Widgets.ActivityCollector;

/**
 * 还款成功
 * Created by Administrator on 2016/5/25.
 */

public class RepaySuccessActivity extends BaseActivity implements View.OnClickListener {
    private TextView repay_succeed,repay_money;
    public static boolean flag3_6 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.fragment_repay_success);
        initView();
        initEvents();
    }
    public void initView(){
        repay_succeed = (TextView)findViewById(R.id.repay_succeed);
        repay_money = (TextView)findViewById(R.id.repay_sucess_money);
        repay_money.setText(LatestPayActivity.money);
    }
    public void initEvents(){
        repay_succeed.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.repay_succeed:
                flag3_6 = true;
                startActivity(new Intent(RepaySuccessActivity.this, MainActivity.class));
                ActivityCollector.finishAll();
            //    ActivityManagerApplication.destoryActivity(MainActivity);
                break;
        }
    }
}
