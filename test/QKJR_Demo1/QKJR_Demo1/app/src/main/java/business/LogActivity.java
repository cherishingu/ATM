package business;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONException;
import org.json.JSONObject;

import Utils.ActivityManagerApplication;
import Utils.ActivitySetsUtils;
import Utils.DataUtils;
import Utils.LogUtils;
import Utils.SharePreferenceUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/11.
 */
public class LogActivity extends Activity {
    private Toolbar toolbar;
    private Button loginBtn;
    private TextView toRegist;
    private EditText nameEdit,pwdEdit;
    private ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        initViews();
        initListener();

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initViews(){
        loginBtn= (Button) findViewById(R.id.login);
        toRegist= (TextView) findViewById(R.id.to_regist);
        nameEdit= (EditText) findViewById(R.id.phone_num_log);
        pwdEdit= (EditText) findViewById(R.id.pwd_log);
        backImg= (ImageView) findViewById(R.id.back_login);

        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){

                case R.id.login:
                    LogUtils.logD("vertify ok");
                    try {
                        if(vertify(nameEdit.getText().toString(),pwdEdit.getText().toString())){


                            ActivityManagerApplication.destoryActivity(ActivitySetsUtils.mainkey);

                            Intent intent = new Intent(LogActivity.this, ActivitySetsUtils.logToActivity);
                            startActivity(intent);
                            //改变账号是否在线的状态值.
                            DataUtils.updateState(LogActivity.this, nameEdit.getText().toString(), true);
                            DataUtils.updateUser(LogActivity.this, nameEdit.getText().toString());

                            finish();
                        }
                        else{
                            Toast.makeText(LogActivity.this, "密码输入错误", Toast.LENGTH_SHORT).show();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    break;

                case R.id.to_regist:
                    Intent intent1 =new Intent(LogActivity.this,RegistActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.back_login:
                    onBackPressed();
                    break;

//                case R.id.btn_vertify:
//                    getVertifyBtn.start();
//                    new Thread(){
//                        public void run(){
//                            String jsonResult = SmsUtils.request("请勿告诉他人，验证码：432100。【乾康金融】");
//                            LogUtils.logD(jsonResult);
//                           // getVertifyBtn.setClickable(false);
//                        }
//                    }.start();
//                    break;
            }
        }
    };

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
    private void initListener(){
        loginBtn.setOnClickListener(listener);
        toRegist.setOnClickListener(listener);
        backImg.setOnClickListener(listener);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivitySetsUtils.fragmentValue=0;
    }



}
