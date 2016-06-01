package business;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkjr_demo2.R;

import Fragment.FirstFragment;
import Fragment.ShoppingFragment;
import Utils.SharePreferenceUtils;
import Widgets.ActivityCollector;

/**
 *全额付款的提交订单
 * Created by wangfeng on 2016/5/24.
 */
public class ConfirmOrderActivity extends Activity implements View.OnClickListener {
    private Button btn_confirmorder;
    private TextView text_name,text_adress,text_goodsname,text_goodsmoney,text_staging;
    private ImageView img_back,img_order;
    SharePreferenceUtils shared,shared2;
    private String NAME = "name";
    private String ADRESS = "adress";
   private int id;
    private String good_name;
    private String good_money;
    public  static int number;
    public   static double paysale;
    public   static int flag ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allpay_confirm);
        ActivityCollector.addActivity(this);
        initView();
        show();
        initEvents();
    }

    private void show() {
        shared2 = new SharePreferenceUtils(this,"firstfragment");
        shared = new SharePreferenceUtils(ConfirmOrderActivity.this,"newAdress");
        String name = shared.getString("name");
        String adress = shared.getString("adress");
        good_name = FirstFragment.name;
        good_money = FirstFragment.money;
        number = ShoppingFragment.number;
        paysale = ShoppingFragment.paysale;
        id = FirstFragment.imgid;
        System.out.println("+++++++++++++++++++ConfirmOrder"+name);
        System.out.println("+++++++++++++++++++ConfirmOrder"+adress);
        System.out.println("+++++++++++++++++++ConfirmOrder"+good_name);
        System.out.println("+++++++++++++++++++ConfirmOrder"+good_money);
        text_goodsname.setText(good_name);
        text_goodsmoney.setText(good_money);
        text_name.setText(name);
        text_adress.setText(adress);
        img_order.setBackgroundResource(id);
        text_staging.setText("付款方式：分期付款  "+paysale+"×"+number+"期（含服务费）");
        if(flag==1){
            text_staging.setVisibility(View.VISIBLE);

        }


    }

    private void initEvents() {
        btn_confirmorder.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }
    private void initView(){
        text_name = (TextView)findViewById(R.id.order_adress_name) ;
        text_adress = (TextView)findViewById(R.id.order_adress_content) ;
        text_goodsmoney = (TextView)findViewById(R.id.text_confirmorder_money);
        text_goodsname = (TextView)findViewById(R.id.text_confirmorder_name);
        text_staging = (TextView)findViewById(R.id.confirm_staging_order) ;
        btn_confirmorder = (Button)findViewById(R.id.confirm_buy);
        img_back = (ImageView)findViewById(R.id.allpay_confirm_back);
        img_order = (ImageView)findViewById(R.id.img_confirmorder);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_buy:
                startActivity(new Intent(ConfirmOrderActivity.this, PaymentActivity.class));
                break;
            case R.id.allpay_confirm_back:
                startActivity(new Intent(ConfirmOrderActivity.this, DeliveryAdressActivity.class));
                break;

        }


    }
}
