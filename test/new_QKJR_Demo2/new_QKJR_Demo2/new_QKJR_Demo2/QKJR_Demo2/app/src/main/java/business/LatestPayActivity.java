package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkjr_demo2.MainActivity;
import com.qkjr_demo2.MainActivity3;
import com.qkjr_demo2.R;

import Widgets.ActivityCollector;

/**
 * 近三十天还款
 * Created by Administrator on 2016/5/24.
 */

public class LatestPayActivity extends BaseActivity implements View.OnClickListener{
    private ImageView latest_pay_back;
    private Button full_repay_stay;
    private Button full_repay_all;
    private TextView full_repay_click,text_name,text_money;
    public static String name,money;

    public static boolean flag3_4 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_latest_pay);
        initView();
        initEvents();
    }
    public void initView(){
        latest_pay_back = (ImageView)findViewById(R.id.latest_pay_back);
        full_repay_stay = (Button)findViewById(R.id.full_repay_stay);
        full_repay_all = (Button)findViewById(R.id.full_repay_all);
        full_repay_click = (TextView)findViewById(R.id.full_repay_click);
        text_name  = (TextView)findViewById(R.id.least_name);
        text_money = (TextView)findViewById(R.id.least_money);
        name = text_name.getText().toString();
        money = text_money.getText().toString();
    }
    public void initEvents(){
        latest_pay_back.setOnClickListener(this);
        full_repay_click.setOnClickListener(this);
        full_repay_all.setOnClickListener(this);
        full_repay_stay.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.latest_pay_back:
                flag3_4 = true;
                startActivity(new Intent(LatestPayActivity.this, MainActivity.class));
                ActivityCollector.finishAll();
                break;
            case R.id.full_repay_all:
                startActivity(new Intent(LatestPayActivity.this, FullRepayActivity.class));
                break;
            case R.id.full_repay_click:
                startActivity(new Intent(LatestPayActivity.this, RepaymentAllActivity.class));
                break;
        }
    }

}
