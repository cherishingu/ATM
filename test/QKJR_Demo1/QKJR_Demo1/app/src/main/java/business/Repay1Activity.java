package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import Utils.DataUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/16.
 */
public class Repay1Activity extends Activity {
    private TextView repayOnceTev;//*****************************
    private TextView cardBondTev;//***************************
    private TextView userCountName;//**************************
    private  String data;//***************************
    Bundle dataBundle;//*************************

    private ImageView backImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay1);
        dataBundle=getIntent().getBundleExtra("data");          //*************************
        data=dataBundle.getString(DataUtils.repayOnce);//*************************
        initViews();
    }
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.repay_now2:
                    Intent intent=new Intent(Repay1Activity.this,Repay2Activity.class);
                    intent.putExtra("data",dataBundle);//*************************
                    startActivity(intent);
                    finish();
                    break;
                case R.id.back_repay1:
                    onBackPressed();
                    break;
            }
        }
    };
    private Button repayBtn;
    private void initViews() {
        repayBtn= (Button) findViewById(R.id.repay_now2);
        repayOnceTev=(TextView)findViewById(R.id.repay_once_tev);//******************
        cardBondTev=(TextView)findViewById(R.id.card_bond_tev);//*******************
        userCountName=(TextView)findViewById(R.id.user_count_name);//***********************
        userCountName.setText(DataUtils.getCurrentUser(this));//*************************
        repayOnceTev.setText(data);//**********************************
        repayBtn.setOnClickListener(listener);
        backImg= (ImageView) findViewById(R.id.back_repay1);
        backImg.setOnClickListener(listener);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);

    }
}
