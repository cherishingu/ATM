package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qkjr_demo2.MainActivity;
import com.qkjr_demo2.R;

import Widgets.ActivityCollector;

/**付款成功
 * Created by Administrator on 2016/5/24.
 */
public class PaySucessActivity extends Activity implements View.OnClickListener {
    private Button btn_sucess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pay_success);
        /************************************************************/
        ActivityCollector.addActivity(this);
        /****************************************************************************/
        initView();
        initEvents();
    }

    private void initEvents() {
        btn_sucess.setOnClickListener(this);
    }
    private void initView(){
        btn_sucess = (Button)findViewById(R.id.pay_success);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pay_success:
                startActivity(new Intent(PaySucessActivity.this, MainActivity.class));
                ActivityCollector.finishAll();
                break;

        }

    }
}
