package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qkjr_demo2.R;

/**
 * 找回密码
 * Created by Administrator on 2016/5/25.
 */

public class SetNewPwdActivity extends Activity implements View.OnClickListener {
    private Button setPwd;
    private ImageView set_newpwd_back;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_newpwd);
        initView();
        initEvents();
    }
    public void initView(){
        setPwd = (Button)findViewById(R.id.set_pwd);
        set_newpwd_back = (ImageView)findViewById(R.id.set_newpwd_back);
    }
    public void initEvents(){
        setPwd.setOnClickListener(this);
        set_newpwd_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.set_pwd:
                startActivity(new Intent(SetNewPwdActivity.this, LogActivity.class));
                break;
            case R.id.set_newpwd_back:
                startActivity(new Intent(SetNewPwdActivity.this, LogActivity.class));
                break;
        }
    }
}
