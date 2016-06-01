package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkjr_demo2.R;

import Widgets.ActivityCollector;

/**
 * 输入银行卡密码
 * Created by wangfeng on 2016/5/24.
 */
public class OrderPayActivity extends Activity implements View.OnClickListener {
    private Button  btn_orderpay;
    private ImageView img_back;
    private EditText edit_pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_payment);
        /************************************************************/
        ActivityCollector.addActivity(this);
        /****************************************************************************/
        initView();
        initEvents();
    }

    private void initEvents() {

//        else{
            btn_orderpay.setOnClickListener(this);
//        }

        img_back.setOnClickListener(this);
    }
    private void initView(){
        btn_orderpay = (Button)findViewById(R.id.log_in);
        edit_pwd = (EditText)findViewById(R.id.pwd_log) ;
        img_back = (ImageView)findViewById(R.id.order_payment_back);

    }
    @Override
    public void onClick(View view) {
//        if (edit_pwd.getText().toString().equals("")) {
//            Toast.makeText(OrderPayActivity.this, "请输入银行密码", Toast.LENGTH_SHORT).show();
//        } else {


            switch (view.getId()) {
                case R.id.log_in:
                    startActivity(new Intent(OrderPayActivity.this, PaySucessActivity.class));
                    break;
                case R.id.order_payment_back:
                    startActivity(new Intent(OrderPayActivity.this, ConfirmOrderActivity.class));
                    break;


            }

        }
    }
//}
