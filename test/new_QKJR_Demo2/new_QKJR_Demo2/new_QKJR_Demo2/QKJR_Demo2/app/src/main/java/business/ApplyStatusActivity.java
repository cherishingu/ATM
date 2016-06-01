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

import Fragment.ShoppingFragment;
import Widgets.ActivityCollector;

/**
 * 申请状态
 * Created by Administrator on 2016/5/24.
 */

public class ApplyStatusActivity extends Activity implements View.OnClickListener {
    private ImageView applyStatusBack;
    private TextView text_applystatus_name1,text_applystatus_money1,text_applystatus_number1,text_applyname2,text_applymoney2,text_applynumber2,text_apply_status1,text_apply_date1,text_apply_status2,text_apply_date2;
    private Button btn_pay;
    private String name1,name2,money1,money2;
    private String number1,number2;
    public static boolean flag3_3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_apply_status);
        initView();

        initEvents();
    }
    public void initView(){
        applyStatusBack = (ImageView)findViewById(R.id.apply_status_back);
        text_applystatus_name1 = (TextView)findViewById(R.id.text_apply_status_name1);
        text_applyname2 = (TextView)findViewById(R.id.text_apply_status_name2);
        text_applystatus_money1 = (TextView)findViewById(R.id.text_apply_status_money1);
        text_applymoney2 = (TextView)findViewById(R.id.text_apply_status_money2);
        text_applystatus_number1 = (TextView)findViewById(R.id.text_apply_status_number1);
        text_applynumber2 = (TextView)findViewById(R.id.text_apply_status_number2);
        btn_pay = (Button)findViewById(R.id.btn_applystatus_pay);
        text_apply_status1 = (TextView)findViewById(R.id.text_apply_status1) ;
        text_apply_date1 = (TextView)findViewById(R.id.text_apply_status_date1) ;
        text_apply_status2= (TextView)findViewById(R.id.text_apply_status2) ;
        text_apply_date2 = (TextView)findViewById(R.id.text_apply_status_date2) ;

       show();

    }

    private void show() {
        name1 = ShoppingFragment.name;
        money1 = ShoppingFragment.paysale+"";
        number1 = ShoppingFragment.number+"";
        System.out.println("++++++++++++++++++++++++name2="+name2);
        System.out.println("++++++++++++++++++++++++money2="+money2);
        System.out.println("++++++++++++++++++++++++number2="+number2);
        text_applystatus_name1.setText(name1);
        text_applystatus_money1.setText(money1);
        text_applystatus_number1.setText(number1);
        text_apply_status1.setText("审核通过");

    }

    public void initEvents(){
        applyStatusBack.setOnClickListener(this);
        btn_pay.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.apply_status_back:
                flag3_3 = true;
                startActivity(new Intent(ApplyStatusActivity.this, MainActivity.class));
                ActivityCollector.finishAll();
                break;
            case  R.id.btn_applystatus_pay:
                startActivity(new Intent(ApplyStatusActivity.this,ConfirmStagingActivity.class));
                break;
        }
    }
}
