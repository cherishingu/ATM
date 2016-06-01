package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qkjr_demo2.R;

import org.json.JSONException;
import org.json.JSONObject;

import Utils.DataUtils;
import Utils.SharePreferenceUtils;
import Utils.SmsUtils;
import Widgets.ActivityCollector;
import Widgets.TimeCountView;

/**
 *找回密码之下一步
 * Created by Administrator on 2016/5/25.
 */

public class PwdLogActivity extends Activity implements View.OnClickListener {
    private Button pwd_log;
    private TimeCountView timeCountView;
    private EditText phoneNum, identityCode;
    private String numVertify;
    private ImageView pwd_log_back;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_log);
        ActivityCollector.addActivity(this);
        initView();
        initEvents();
    }
    public void initView(){
        pwd_log = (Button)findViewById(R.id.pwd_log);
        timeCountView = (TimeCountView)findViewById(R.id.pwd_time_count);
        phoneNum = (EditText)findViewById(R.id.pwd_phone_num);
        identityCode = (EditText)findViewById(R.id.pwd_identity_code);
        pwd_log_back = (ImageView)findViewById(R.id.pwd_log_back);
    }
    public void initEvents(){
        pwd_log.setOnClickListener(this);
        timeCountView.setOnClickListener(this);
        phoneNum.setOnClickListener(this);
        identityCode.setOnClickListener(this);
        pwd_log_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.pwd_log_back:
                startActivity(new Intent(PwdLogActivity.this, LogActivity.class));
                break;
            case R.id.pwd_log:
                if(numVertify.compareTo(identityCode.getText().toString())==0){
                    Intent intent=new Intent(PwdLogActivity.this,SetNewPwdActivity.class);
                    startActivity(intent);
                    SharePreferenceUtils utils=new SharePreferenceUtils(PwdLogActivity.this,DataUtils.shareName);
                    JSONObject json=new JSONObject();
                    try {
                        json.put(DataUtils.account, phoneNum.getText().toString().trim());
                        json.put(DataUtils.pwd,pwd_log.getText().toString().trim());
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                    utils.putString(phoneNum.getText().toString().trim(),json.toString());
                    utils.commit();
//                        utils.putString("password",pwdEdit.getText().toString().trim());
                    timeCountView.stop();
                    finish();
                }
                else{
                    Toast.makeText(PwdLogActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.pwd_time_count:
              /*  if(phoneNumEdit.getText().toString().trim().length()<11) {
                    Toast.makeText(RegistActivity.this, "手机号码填写不正确!", Toast.LENGTH_SHORT).show();
                    break;
                }
                timeCountView.start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        numVertify= DataUtils.generate6Random();
                        SmsUtils.request(phoneNumEdit.getText().toString().trim(),"请勿告诉他人，验证码："+
                                numVertify+"。【乾康金融】");
                    }
                }).start();
                break;*/
                if (phoneNum.getText().toString().trim().length() < 11){
                    Toast.makeText(PwdLogActivity.this, "手机号码填写不正确！", Toast.LENGTH_SHORT).show();
                    break;
                }
                timeCountView.start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        numVertify = DataUtils.generate6Random();
                        SmsUtils.request(phoneNum.getText().toString().trim(), "请勿告诉他人，验证码："
                                + numVertify + "。【乾康金融】");
                    }
                }).start();
                break;
        }
    }
}
