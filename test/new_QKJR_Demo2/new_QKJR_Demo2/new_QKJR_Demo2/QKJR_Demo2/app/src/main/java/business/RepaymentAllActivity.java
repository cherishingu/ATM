package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkjr_demo2.R;

import Widgets.ActivityCollector;

/**
 * 点击还款
 * Created by Administrator on 2016/5/25.
 */

public class RepaymentAllActivity extends BaseActivity implements View.OnClickListener {
    private ImageView repayBack;
    private Button confirm;
    private String money;
    private TextView text_money;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_repayment_all);
        initView();
        initEvents();
    }
    public void initView(){
        repayBack = (ImageView)findViewById(R.id.repayment_all_back);
        confirm = (Button)findViewById(R.id.confirm_repay);
        text_money = (TextView)findViewById(R.id.text_repayall_money);
        text_money.setText("付款金额：   "+ LatestPayActivity.money+"元");
    }
    public void initEvents(){
        repayBack.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.repayment_all_back:
                startActivity(new Intent(RepaymentAllActivity.this, LatestPayActivity.class));
                break;
            case R.id.confirm_repay:
                startActivity(new Intent(RepaymentAllActivity.this, InputPwdActivity.class));
        }
    }
}
