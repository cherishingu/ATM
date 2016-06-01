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
import Widgets.ActivityCollector;

/**付款金额
 * Created by ad on 2016/5/27.
 */
public class PaymentActivity extends Activity implements View.OnClickListener {
    private Button btn_confirm;
    private ImageView img_back;
    private TextView text_money;
    private String money;
    public static int flag =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_all);
        /************************************************************/
        ActivityCollector.addActivity(this);
        /****************************************************************************/
        InitView();
        show();
        InitEvents();
    }

    private void show() {
       if(flag ==0){
           money = FirstFragment.money;
           text_money.setText("付款金额：     "+money);
       }
        else{
           money = ConfirmOrderActivity.paysale+"";
           text_money.setText("付款金额：     "+money);

       }
    }

    private void InitEvents() {
        btn_confirm.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }
    private void InitView() {
        btn_confirm = (Button)findViewById(R.id.confirm_pay);
        img_back =(ImageView)findViewById(R.id.payment_all_back);
        text_money = (TextView)findViewById(R.id.text_payall_money);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_pay:
                startActivity(new Intent(PaymentActivity.this, OrderPayActivity.class));
                break;
            case R.id.payment_all_back:
                startActivity(new Intent(PaymentActivity.this, ConfirmOrderActivity.class));
                break;
        }

    }
}
