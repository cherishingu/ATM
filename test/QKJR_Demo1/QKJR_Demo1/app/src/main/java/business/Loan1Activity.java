package business;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/14.
 */

public class Loan1Activity extends Activity {

    private TextView needUnderLine1,needUnderLine2;
    private ImageView agreeImg;
    private boolean agree=true;
    private Button nextStepBtn;
    private ImageView backImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_1);
        initViews();

    }

    private void initViews(){
        backImg= (ImageView) findViewById(R.id.back_loan1);
        backImg.setOnClickListener(listener);
        needUnderLine1= (TextView) findViewById(R.id.need_underline1);
        needUnderLine2=(TextView) findViewById(R.id.need_underline2);
        UIUtils.addUnderLine(needUnderLine1);
        agreeImg= (ImageView) findViewById(R.id.agree);
        agreeImg.setOnClickListener(listener);
        nextStepBtn= (Button) findViewById(R.id.loan1_next_step);
        nextStepBtn.setOnClickListener(listener);
        //UIUtils.addUnderLine(needUnderLine2);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.agree:
                    if(agree){
                        agree=false;
                        agreeImg.setImageDrawable(getResources().getDrawable(R.mipmap.protocol_disagree));
                    }
                    else{
                        agree=true;
                        agreeImg.setImageDrawable(getResources().getDrawable(R.mipmap.protocol_agree));
                    }

                    break;
                case R.id.loan1_next_step:
                    finish();
                    Intent intent=new Intent(Loan1Activity.this,VertifyIdentifyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.back_loan1:
                    onBackPressed();
                    break;
            }
        }
    };
}
