package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkjr_demo2.MainActivity;

import com.qkjr_demo2.R;

import org.json.JSONException;
import org.json.JSONObject;

import Utils.ActivityManagerApplication;
import Utils.ActivitySetsUtils;
import Utils.DataUtils;
import Utils.LogUtils;
import Utils.SharePreferenceUtils;
import Widgets.ActivityCollector;

/**
 * 登录
 * Created by Administrator on 2016/5/24.
 */

public class LogActivity extends Activity implements View.OnClickListener{
    public static boolean flag3_7 = false;

    private Button log_in;
    private TextView log_find, log_regist;
    private EditText nameEdit,pwdEdit;
    private ImageView log_visible, log_back;
    private boolean isClick = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(R.layout.activity_log);
        initView();
        initEvents();
    }
    public void initView(){
        log_in = (Button)findViewById(R.id.log_in);
        log_find = (TextView)findViewById(R.id.log_find);
        log_regist = (TextView)findViewById(R.id.log_regist);
        nameEdit= (EditText) findViewById(R.id.phone_num_log);
        pwdEdit= (EditText) findViewById(R.id.pwd_log);
        log_visible = (ImageView)findViewById(R.id.log_visible);
        log_back = (ImageView)findViewById(R.id.log_back);
    }
    public void initEvents(){
        log_in.setOnClickListener(this);
        log_find.setOnClickListener(this);
        log_regist.setOnClickListener(this);
        log_visible.setOnClickListener(this);
        log_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.log_back:
                startActivity(new Intent(LogActivity.this, MainActivity.class));
                ActivityCollector.finishAll();
                break;
            case R.id.log_in:

                LogUtils.logD("vertify ok");
                try {
                    if(vertify(nameEdit.getText().toString(),pwdEdit.getText().toString())){
                        ActivityManagerApplication.destoryActivity(ActivitySetsUtils.mainkey);
                        flag3_7 = true;
                        Intent intent = new Intent(LogActivity.this, MainActivity.class);
                        startActivity(intent);
                        //改变账号是否在线的状态值.
                        DataUtils.updateState(LogActivity.this, nameEdit.getText().toString(), true);
                        DataUtils.updateUser(LogActivity.this, nameEdit.getText().toString());

                        ActivityCollector.finishAll();
                    }
                    else{
                        Toast.makeText(LogActivity.this, "密码输入错误", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.log_visible:
                pwdEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case R.id.log_find:
                startActivity(new Intent(LogActivity.this, PwdLogActivity.class));
                break;
            case R.id.log_regist:
                startActivity(new Intent(LogActivity.this, RegistActivity.class));
                break;
        }
    }
    private boolean vertify(String account,String pwd) throws JSONException {
        SharePreferenceUtils utils=new SharePreferenceUtils(LogActivity.this, DataUtils.shareName);
        String info=utils.getString(account);
        LogUtils.logD(info);
        JSONObject infoJson=new JSONObject(info);
        String password=infoJson.getString(DataUtils.pwd);
        if(password.compareTo(pwd)==0){
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivitySetsUtils.fragmentValue=0;
    }
}
