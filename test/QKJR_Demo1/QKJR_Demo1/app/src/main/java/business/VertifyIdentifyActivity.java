package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zhuzhuxia.qkjr_demo1.R;

import Utils.DataUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/14.
 */
public class VertifyIdentifyActivity extends Activity {
    private EditText phoneNumIdentify;//***************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertify_identify);
        initViews();
        initListeners();
    }
    private Button nextbtn;
    private ImageView backImg;
    private void initViews(){
        nextbtn= (Button) findViewById(R.id.loan1_next_step);
        phoneNumIdentify=(EditText)findViewById(R.id.phone_num_identify);//**************************
        phoneNumIdentify.setText(DataUtils.getCurrentUser(this));//***************************
        backImg= (ImageView) findViewById(R.id.back_ver2);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
    }

    /**
     *
     */
    private void initListeners(){
        nextbtn.setOnClickListener(listener);
        backImg.setOnClickListener(listener);
    }
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.loan1_next_step:
                    finish();
                    Intent intent=new Intent(VertifyIdentifyActivity.this,AuthenticationActivity.class);
                    startActivity(intent);

                    break;
                case R.id.back_ver2:
                    onBackPressed();
                    break;
            }
        }
    };
}
