package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkjr_demo2.R;

import Fragment.ShoppingFragment;

/**
 * Created by Administrator on 2016/5/31.
 */
public class ConfirmStagingActivity extends Activity implements View.OnClickListener {
    private TextView text_name,text_money,text_staging;
    private Button btn_pay;
    private ImageView img;
    private String name,money;
    private int id,number;
    private double paysale;
    public static int flag = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        intiView();
        initEvents();
    }

    private void initEvents() {
        btn_pay.setOnClickListener(this);
    }

    private void intiView() {
        text_name = (TextView)findViewById(R.id.text_confirmstaging_name);
        text_money = (TextView)findViewById(R.id.text_confirmstaging_money);
        text_staging = (TextView)findViewById(R.id.text_staging);
        btn_pay = (Button)findViewById(R.id.confirmstaging_buy);
        img = (ImageView)findViewById(R.id.img_confirmstaging);
        show();
    }

    private void show() {
        name = ShoppingFragment.name;
        money=ShoppingFragment.money;
        id = ShoppingFragment.id;

        number = ShoppingFragment.number;
        paysale = ShoppingFragment.paysale;
        img.setBackgroundResource(id);
        text_name.setText(name);
        text_money.setText(money);
        text_staging.setText("付款方式：分期付款 " +paysale +"×"+number+"期（含服务费）");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirmstaging_buy:
                ConfirmOrderActivity.flag = 1;
                PaymentActivity.flag = 1;
                startActivity(new Intent(ConfirmStagingActivity.this,DeliveryAdressActivity.class));
                break;
        }

    }
}
