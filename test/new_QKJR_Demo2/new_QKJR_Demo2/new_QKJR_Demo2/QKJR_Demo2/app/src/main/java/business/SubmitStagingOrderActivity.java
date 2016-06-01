package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qkjr_demo2.R;

/**
 * 分期付款
 * Created by wangfeng on 2016/5/24.
 */
public class SubmitStagingOrderActivity extends Activity implements View.OnClickListener {
    private Button btn_submit_staging;
    private ImageView img_staging_back;
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.person_detail_save:
                startActivity(new Intent(SubmitStagingOrderActivity.this, CreditConfirmActivity.class));
                break;
            case  R.id.personal_detail_back:
                startActivity(new Intent(SubmitStagingOrderActivity.this, ShoppingDetailActivity.class));
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);
        initView();
        initEvents();

    }

    private void initEvents() {
        btn_submit_staging.setOnClickListener(this);
        img_staging_back.setOnClickListener(this);
    }
    private void initView() {
        btn_submit_staging = (Button)findViewById(R.id.person_detail_save);
        img_staging_back = (ImageView)findViewById(R.id.personal_detail_back);
    }
}
