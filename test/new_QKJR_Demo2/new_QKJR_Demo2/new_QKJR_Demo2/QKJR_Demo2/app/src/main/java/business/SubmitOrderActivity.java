package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkjr_demo2.R;

import Fragment.FirstFragment;
import Utils.SharePreferenceUtils;
import Widgets.ActivityCollector;

/**
 * 立即付款
 * Created by wangfeng on 2016/5/24.
 */
public class SubmitOrderActivity extends Activity implements View.OnClickListener {
    private Button btn_confirm_order;
    private TextView text_staging,text_name,text_monry;
    private ImageView img_back,img_order,img;
    public static int flag = 0;
//    private String NAME = "firstFragment_name";
//    private String MONEY = "firstFragment_money";
//    private String ID ="firstFragment_id";
//    public static int imgid;
//    SharePreferenceUtils shared;
    String name,money;
    private int id;
//    SharePreferenceUtils shared = new SharePreferenceUtils(this,"staging");
//    private String staging = "STAGING";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /************************************************************/
        ActivityCollector.addActivity(this);
        /****************************************************************************/
        setContentView(R.layout.activity_allpay_payment);
        initView();
        initEvents();
    }


    private void initEvents() {
        btn_confirm_order.setOnClickListener(this);
        img_back.setOnClickListener(this);
        img_order.setOnClickListener(this);




    }
    private void initView(){
        btn_confirm_order = (Button)findViewById(R.id.confirm_allpay_buy);
        img_back = (ImageView)findViewById(R.id.confirm_allpay_back);
        img_order = (ImageView) findViewById(R.id.confirm_add);
      text_name = (TextView)findViewById(R.id.text_submit_order_name);
        text_monry = (TextView)findViewById(R.id.text_submit_order_money);
        img = (ImageView)findViewById(R.id.img_submit_order);
        show();

    }

    private void show() {
//        shared = new SharePreferenceUtils(this,"firstfragment");
        name = FirstFragment.name;
        money =FirstFragment.money;
        id = FirstFragment.imgid;
        System.out.println("+++++++++++++++++++++++++++++++++"+name);
        System.out.println("+++++++++++++++++++++++++++++++++"+money);
        System.out.println("+++++++++++++++++++++++++++++++++"+FirstFragment.imgid);
            text_name.setText(name);
            text_monry.setText(money);
            img.setBackgroundResource(id);

    }


    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.confirm_allpay_buy:
            ConfirmOrderActivity.flag = 0;
            PaymentActivity.flag=0;
            startActivity(new Intent(SubmitOrderActivity.this, DeliveryAdressActivity.class));
            break;
        case R.id.confirm_allpay_back:
            startActivity(new Intent(SubmitOrderActivity.this, ShoppingDetailActivity.class));
            //////////////////////////////////////////////////////////////////////////////////////////////
            ActivityCollector.finishAll();
            break;
        case R.id.confirm_add:
            startActivity(new Intent(SubmitOrderActivity.this, DeliveryAdressActivity.class));
            break;

    }



    }
}
