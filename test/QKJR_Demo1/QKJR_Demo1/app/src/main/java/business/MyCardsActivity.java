package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zhuzhuxia.qkjr_demo1.R;

import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/16.
 */
public class MyCardsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycards);
        initViews();
    }
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.binds_linear:
                    Intent intent=new Intent(MyCardsActivity.this,BindBankCards.class);
                    startActivity(intent);
                    break;
                case R.id.unbinds_linear:
                    Intent intent1=new Intent(MyCardsActivity.this,UnBindCardActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
                case R.id.back_mycards:
                    onBackPressed();
                    break;
            }
        }
    };

    private LinearLayout bindCards,unbindCards;
    private ImageView backImg;
    private void initViews(){
        bindCards= (LinearLayout) findViewById(R.id.binds_linear);
        unbindCards=(LinearLayout)findViewById(R.id.unbinds_linear);
        backImg= (ImageView) findViewById(R.id.back_mycards);

        bindCards.setOnClickListener(listener);
        unbindCards.setOnClickListener(listener);
        backImg.setOnClickListener(listener);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);

    }

}
