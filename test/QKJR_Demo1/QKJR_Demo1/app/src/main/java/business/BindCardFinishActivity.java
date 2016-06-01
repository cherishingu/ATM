package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zhuzhuxia.qkjr_demo1.R;

import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/16.
 */
public class BindCardFinishActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard2);
        initViews();
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back_card2:
                    onBackPressed();
                    break;
                case R.id.commit_btn1:
                    finish();
                    Intent intent=new Intent(BindCardFinishActivity.this,MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    private ImageView backImg;
    private Button commitBtn;
    private void initViews(){
        backImg= (ImageView) findViewById(R.id.back_card2);
        backImg.setOnClickListener(listener);
        commitBtn= (Button) findViewById(R.id.commit_btn1);
        commitBtn.setOnClickListener(listener);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
    }
}
