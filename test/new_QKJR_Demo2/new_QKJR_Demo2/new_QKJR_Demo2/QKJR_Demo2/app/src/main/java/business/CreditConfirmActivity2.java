package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.qkjr_demo2.MainActivity;
import com.qkjr_demo2.MainActivity3;
import com.qkjr_demo2.R;

import Fragment.ShoppingFragment;
import Widgets.ActivityCollector;

/**
 * 个人信息之保存信用认证2
 * Created by Administrator on 2016/5/25.
 */


public class CreditConfirmActivity2 extends Activity implements View.OnClickListener {

    public static boolean flag3 = false;

    private Button creditFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_credit_confirm2);
        initView();
        initEvents();
    }
    public void initView(){
        creditFinish = (Button)findViewById(R.id.credit_finish);
    }
    public void initEvents(){
        creditFinish.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.credit_finish:
                flag3 = true;
                ActivityCollector.finishAll();
              ShoppingFragment.toApplystatus = true;
                startActivity(new Intent(CreditConfirmActivity2.this, ApplyStatusActivity.class));
                break;
        }
    }
}
