package business;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.qkjr_demo2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


import Utils.DataUtils;
import Utils.SharePreferenceUtils;
import Utils.SmsContent;
import Utils.SmsUtils;
import Widgets.ActivityCollector;
import Widgets.TimeCountView;

/**
 * 注册
 * Created by Administrator on 2016/5/25.
 */
public class RegistActivity extends Activity {

    private Button registBtn;
    private TimeCountView timeCountView;
    private String numVertify;
    private EditText contentEditText,phoneNumEdit,pwdEdit;
    private ImageView regist_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ActivityCollector.addActivity(this);
        initViews();
    }
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.regist_back:
                    startActivity(new Intent(RegistActivity.this, LogActivity.class));
                    break;
                case R.id.rfgist_time_count:
                    if(phoneNumEdit.getText().toString().trim().length()<11) {
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

                    break;
                case R.id.regist:
                    if(numVertify.compareTo(contentEditText.getText().toString())==0){
                        Intent intent=new Intent(RegistActivity.this,LogActivity.class);
                        startActivity(intent);
                        SharePreferenceUtils utils=new SharePreferenceUtils(RegistActivity.this,DataUtils.shareName);
                        JSONObject json=new JSONObject();
                        try {
                            json.put(DataUtils.account, phoneNumEdit.getText().toString().trim());
                            json.put(DataUtils.pwd,pwdEdit.getText().toString().trim());
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        utils.putString(phoneNumEdit.getText().toString().trim(),json.toString());
                        utils.commit();
//                        utils.putString("password",pwdEdit.getText().toString().trim());
                        timeCountView.stop();
                        finish();
                    }
                    else{
                        Toast.makeText(RegistActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }

        }
    };
    private void initViews(){
        registBtn= (Button) findViewById(R.id.regist);
        contentEditText= (EditText) findViewById(R.id.vertify_content);
        timeCountView= (TimeCountView) findViewById(R.id.rfgist_time_count);
        phoneNumEdit= (EditText) findViewById(R.id.phone_num);
        pwdEdit= (EditText) findViewById(R.id.regist_pwd);
        regist_back = (ImageView) findViewById(R.id.regist_back);

        regist_back.setOnClickListener(listener);
        registBtn.setOnClickListener(listener);
        timeCountView.setOnClickListener(listener);
        SmsContent content=new SmsContent(this,new Handler(),contentEditText);
        this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, content);
    }


}
