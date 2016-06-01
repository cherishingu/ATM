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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import Utils.ActivitySetsUtils;
import Utils.DataUtils;
import Utils.SharePreferenceUtils;
import Utils.SmsContent;
import Utils.SmsUtils;
import Utils.UIUtils;
import Widgets.TimeCountView;

/**
 * Created by zhuzhuxia on 16/5/11.
 */
public class RegistActivity extends Activity {


    private Button registBtn;
    private TimeCountView timeCountView;
    private ImageView backImg;

    private EditText contentEditText,phoneNumEdit,pwdEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initViews();

    }
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.time_count:
                    if(phoneNumEdit.getText().toString().trim().length()<11) {
                        Toast.makeText(RegistActivity.this, "手机号码填写不正确!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    timeCountView.start();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            ActivitySetsUtils.vertify=DataUtils.generate6Random();

                            SmsUtils.request(phoneNumEdit.getText().toString().trim(),"请勿告诉他人，验证码："+
                                                                        ActivitySetsUtils.vertify+"。【乾康金融】");

                        }
                    }).start();

                    break;
                case R.id.regist:
                    if(ActivitySetsUtils.vertify!=null&&ActivitySetsUtils.vertify.compareTo(contentEditText.getText().toString())==0){
                        Intent intent=new Intent(RegistActivity.this,MainActivity.class);
                        startActivity(intent);
                        SharePreferenceUtils utils=new SharePreferenceUtils(RegistActivity.this,DataUtils.shareName);
                        JSONObject json=new JSONObject();
                        try {
                            json.put(DataUtils.account, phoneNumEdit.getText().toString().trim());
                            json.put(DataUtils.pwd, pwdEdit.getText().toString().trim());
                            json.put(DataUtils.online,true);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        utils.putString(phoneNumEdit.getText().toString().trim(),json.toString());
                        utils.putString(DataUtils.currentUser,phoneNumEdit.getText().toString().trim());
                        utils.commit();
//                        utils.putString("password",pwdEdit.getText().toString().trim());
                        timeCountView.stop();
                        finish();
                    }
                    else{
                        Toast.makeText(RegistActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.back_regist:
                    onBackPressed();
                    break;
            }

        }
    };
    private void initViews(){


        registBtn= (Button) findViewById(R.id.regist);
        contentEditText= (EditText) findViewById(R.id.vertify_content);
        timeCountView= (TimeCountView) findViewById(R.id.time_count);
        phoneNumEdit= (EditText) findViewById(R.id.phone_num);
        pwdEdit= (EditText) findViewById(R.id.pwd);
        backImg= (ImageView) findViewById(R.id.back_regist);

        backImg.setOnClickListener(listener);
        registBtn.setOnClickListener(listener);
        timeCountView.setOnClickListener(listener);

        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
    }


}
