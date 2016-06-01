package business;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkjr_demo2.R;

import Utils.SharePreferenceUtils;
import Widgets.ActivityCollector;

/**
 * 全部付款后的地址填写
 * Created by Administrator on 2016/5/24.
 */
public class DeliveryAdressActivity extends Activity implements View.OnClickListener {
    private EditText edit_name, edit_phone,edit_number,edit_adress;
    private Button btn_address_save;
    private ImageView img_back;
    SharePreferenceUtils shared;
    private boolean isComplete = true;
    private String NAME = "name";
    private String ADRESS = "adress";
   private String name,adress,phone,number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        ActivityCollector.addActivity(this);
        initView();
        initEvents();
//        complete();


    }

    @Override
    protected void onResume() {
        super.onResume();


    }
//
//    private void complete() {
//        if (name.equals("")){
//            isComplete =false;
//            Toast.makeText(DeliveryAdressActivity.this,"请输入姓名",Toast.LENGTH_SHORT).show();
//
//        }
//        if(phone.equals("")){
//            isComplete =false;
//            Toast.makeText(DeliveryAdressActivity.this,"请输入您的电话",Toast.LENGTH_SHORT).show();
//        }
//        if(number.equals("")){
//            isComplete =false;
//            Toast.makeText(DeliveryAdressActivity.this,"请输入您的邮编",Toast.LENGTH_SHORT).show();
//        }
//        if(adress.equals("")){
//            isComplete =false;
//            Toast.makeText(DeliveryAdressActivity.this,"请输入您的详细地址",Toast.LENGTH_SHORT).show();
//        }
//
//
//
//    }

    @Override
    protected void onPause() {
        super.onPause();
            getInfo();

    }

    private void getInfo() {
        shared =new SharePreferenceUtils(DeliveryAdressActivity.this,"newAdress");
         name = edit_name.getText().toString();
        phone = edit_phone.getText().toString();
         number = edit_number.getText().toString();
        adress = edit_adress.getText().toString();
            shared.putString(NAME,name);
            System.out.println("name +++++++++++++++++++++"+name);
            shared.putString(ADRESS,adress);
            System.out.println("adress +++++++++++++++++++++"+adress);
            shared.commit();


    }

    private void initEvents() {
        btn_address_save.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }
    private void initView() {

        btn_address_save = (Button)findViewById(R.id.btn_addr_save);
        img_back = (ImageView) findViewById(R.id.new_addr_back);
        edit_name = (EditText)findViewById(R.id.address_name);
        edit_phone = (EditText)findViewById(R.id.address_phone);
        edit_number = (EditText)findViewById(R.id.address_province);
        edit_adress = (EditText)findViewById(R.id.phone_num_log);
    }


    @Override
    public void onClick(View view) {


            switch (view.getId()) {
                case R.id.btn_addr_save:
                        startActivity(new Intent(DeliveryAdressActivity.this, ConfirmOrderActivity.class));
                    break;

//                }
                case R.id.new_addr_back:
                    startActivity(new Intent(DeliveryAdressActivity.this, SubmitOrderActivity.class));
                    break;
            }

        }
    }



