package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qkjr_demo2.MainActivity;
import com.qkjr_demo2.MainActivity3;
import com.qkjr_demo2.R;

import Widgets.ActivityCollector;

/**
 * 我的账单
 * Created by Administrator on 2016/5/24.
 */

public class MyBillActivity extends Activity implements View.OnClickListener{
    private ImageView my_bill_back;
    public static boolean flag3_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bill);
        ActivityCollector.addActivity(this);
        initView();
        initEvents();
    }
    public void initView(){
        my_bill_back = (ImageView)findViewById(R.id.my_bill_back);
    }
    public void initEvents(){
        my_bill_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.my_bill_back:
                flag3_2 = true;
                startActivity(new Intent(MyBillActivity.this, MainActivity.class));
                ActivityCollector.finishAll();
                break;
        }
    }
}
