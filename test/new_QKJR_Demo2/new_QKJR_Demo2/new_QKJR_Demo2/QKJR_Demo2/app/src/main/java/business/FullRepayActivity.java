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

/**我要还款
 * Created by ad on 2016/5/25.
 */

public class FullRepayActivity extends Activity implements View.OnClickListener {
    private ImageView full_repay_back;
    private Button full_repay_stay;
    private Button full_repay_all;
    private TextView full_repay_click;
    public static boolean flag3_5 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_repay);
        ActivityCollector.addActivity(this);
        initView();
        initEvents();
    }
    public void initView(){
        full_repay_back = (ImageView)findViewById(R.id.full_repay_back2);
        full_repay_stay = (Button)findViewById(R.id.full_repay_stay);
        full_repay_all = (Button)findViewById(R.id.full_repay_all);
        full_repay_click = (TextView)findViewById(R.id.full_repay_click);
    }
    public void initEvents(){
        full_repay_back.setOnClickListener(this);
        full_repay_click.setOnClickListener(this);
        full_repay_all.setOnClickListener(this);
        full_repay_stay.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.full_repay_back2:
                flag3_5 = true;
                startActivity(new Intent(FullRepayActivity.this, MainActivity.class));
                ActivityCollector.finishAll();
                break;
            case R.id.full_repay_stay:
                startActivity(new Intent(FullRepayActivity.this, LatestPayActivity.class));
                break;
            case R.id.full_repay_click:
                startActivity(new Intent(FullRepayActivity.this, RepaymentAllActivity.class));
                break;
        }
    }
}
