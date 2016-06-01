package business;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.qkjr_demo2.MainActivity4;
import com.qkjr_demo2.R;

import Widgets.ActivityCollector;

/**
 * 申请退款
 * Created by wangfeng on 2016/5/24.
 */
public class ApplyReturnAcitvity extends Activity  implements View.OnClickListener{
    private Button btn_cancel;
    private ImageView img_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_apply_return);
        InitView();
        InitEvents();
    }

    private void InitEvents() {
        btn_cancel.setOnClickListener(this);
        img_cancel.setOnClickListener(this);

    }
    private void InitView() {
        btn_cancel = (Button)findViewById(R.id.apply_canael_return);
        img_cancel = (ImageView)findViewById(R.id.apply_return_back);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.apply_canael_return:
                startActivity(new Intent(ApplyReturnAcitvity.this, MainActivity4.class));
                ActivityCollector.finishAll();
                Toast.makeText(ApplyReturnAcitvity.this,"取消退货申请成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.apply_return_back:
                startActivity(new Intent(ApplyReturnAcitvity.this, MainActivity4.class));
                ActivityCollector.finishAll();
                break;

        }

    }
}
